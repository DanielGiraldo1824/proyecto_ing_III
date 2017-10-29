<?php

namespace App\Http\Controllers;

use App\DataTables\usersDataTable;
use App\Http\Requests;
use App\Http\Requests\CreateusersRequest;
use App\Http\Requests\UpdateusersRequest;
use App\Repositories\usersRepository;
use Flash;
use App\Http\Controllers\AppBaseController;
use Response;
use App\User;
use HttpOz\Roles\Models\Role;
use Illuminate\Support\Facades\DB;
use App\Models\centers;

class usersController extends AppBaseController
{
    /** @var  usersRepository */
    private $usersRepository;

    public function __construct(usersRepository $usersRepo)
    {
        $this->usersRepository = $usersRepo;
         $this->middleware('auth');
    }

    /**
     * Display a listing of the users.
     *
     * @param usersDataTable $usersDataTable
     * @return Response
     */
    public function index(usersDataTable $usersDataTable)
    {
        return $usersDataTable->render('users.index');
    }

    /**
     * Show the form for creating a new users.
     *
     * @return Response
     */
    public function create()
    {
        $centers = centers::all()->toArray();
        $roles = Role::all()->toArray();
        $rolesNamesList = [];
        for ($i = 0; $i < count($roles); $i++){
            
            $RoleListEvaluated[$i]['id'] = $roles[$i]['id'];
            $RoleListEvaluated[$i]['name'] = $roles[$i]['name'];
            $RoleListEvaluated[$i]['selected'] = false;
        }
        return view('users.create')
                ->with('roles',$RoleListEvaluated)
                ->with('centers',$centers);
    }

    /**
     * Store a newly created users in storage.
     *
     * @param CreateusersRequest $request
     *
     * @return Response
     */
    public function store(CreateusersRequest $request)
    {
        $input = $request->all();
        
       $this->validate($request,[
                                'name' => 'required|max:255',
                                'email' => 'required|email|max:255|unique:users',
                                'password' => 'required|min:6|confirmed',
                            ]);
        
        $user = User::create([
            'name' => $request['name'],
            'email' => $request['email'],
            'centers_id' => $request['center'],
            'password' => bcrypt($request['password']),
        ]);
        
        foreach ($request['role'] as $role) {
            $user->attachRole($role);   
        }
        Flash::success(trans('custom.users_saved'));

        return redirect(route('users.index'));
    }

    /**
     * Display the specified users.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function show($id)
    {
        $users = $this->usersRepository->findWithoutFail($id);

        if (empty($users)) {
            Flash::error(trans('custom.users_not_found'));

            return redirect(route('users.index'));
        }
        
        $userArray = $users->toArray();
        $centerName = $users->centers->name_center;
        
        $roleList = DB::table('role_user')->where('user_id',$userArray['id'])->get()->toArray();
        $rolesNamesList = [];
        //dd($roleList);
        if(count($roleList) > 0){
            foreach ($roleList as $role) {
                $roleName = Role::where('id',$role->role_id)->get()->toArray();
                $rolesNamesList[] = $roleName[0]['name'];
            }
        }
        
        return view('users.show')
                ->with('users', $users)
                ->with('center', $centerName)
                ->with('roleNameList',$rolesNamesList);
    }

    /**
     * Show the form for editing the specified users.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function edit($id)
    {
        $users = $this->usersRepository->findWithoutFail($id);
        
        if (empty($users)) {
            Flash::error(trans('custom.users_not_found'));

            return redirect(route('users.index'));
        }
        $roles = Role::all()->toArray();
        $userArray = $users->toArray();
        $roleList = DB::table('role_user')->where('user_id',$userArray['id'])->get()->toArray();
        $flag = 0;
        $RoleListEvaluated = [];
        for ($i = 0; $i < count($roles); $i++){
            
            foreach ($roleList as $userRole) {
                if($roles[$i]['id'] == $userRole->role_id){
                    $RoleListEvaluated[$i]['id'] = $roles[$i]['id'];
                    $RoleListEvaluated[$i]['name'] = $roles[$i]['name'];
                    $RoleListEvaluated[$i]['selected'] = true;
                    $flag = 1;
                }
            }
            if( $flag == 0){
              $RoleListEvaluated[$i]['id'] = $roles[$i]['id'];
              $RoleListEvaluated[$i]['name'] = $roles[$i]['name'];
              $RoleListEvaluated[$i]['selected'] = false;  
            }
            $flag = 0;
        }
        $centers = centers::all()->toArray();
        return view('users.edit')
                ->with('users', $users)
                ->with('centers', $centers)
                ->with('roles', $RoleListEvaluated);
    }

    /**
     * Update the specified users in storage.
     *
     * @param  int              $id
     * @param UpdateusersRequest $request
     *
     * @return Response
     */
    public function update($id, UpdateusersRequest $request)
    {
        $users = $this->usersRepository->findWithoutFail($id);

        if (empty($users)) {
            Flash::error(trans('custom.users_not_found'));

            return redirect(route('users.index'));
        }
//        $users->name = $request['name'];
//        $users->email = $request['email'];
//        $users->password = bcrypt($request['password']);
//        $users->save();
        //$users = $this->usersRepository->update($request->all(), $id);
        $userInfo = User::find($id);
        $userInfo->name = $request['name'];
        $userInfo->email = $request['email'];
        $userInfo->centers_id = $request['center'];
        $userInfo->password = bcrypt($request['password']);
        $userInfo->save();
        $userInfo->detachAllRoles();
        foreach ($request['role'] as $role) {
            $userInfo->attachRole($role);   
        }
        Flash::success(trans('custom.users_updated'));

        return redirect(route('users.index'));
    }

    /**
     * Remove the specified users from storage.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function destroy($id)
    {
        $users = $this->usersRepository->findWithoutFail($id);

        if (empty($users)) {
            Flash::error(trans('custom.users_not_found'));

            return redirect(route('users.index'));
        }

        $this->usersRepository->delete($id);

        Flash::success(trans('custom.users_deleted'));

        return redirect(route('users.index'));
    }
    
    protected function validator(array $data)
    {
        return Validator::make($data, [
            'name' => 'required|max:255',
            'email' => 'required|email|max:255|unique:users',
            'password' => 'required|min:6|confirmed',
        ]);
    }
}
