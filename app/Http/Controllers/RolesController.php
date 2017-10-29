<?php

namespace App\Http\Controllers;

use App\DataTables\RolesDataTable;
use App\Http\Requests;
use App\Http\Requests\CreateRolesRequest;
use App\Http\Requests\UpdateRolesRequest;
use App\Repositories\RolesRepository;
use Flash;
use App\Http\Controllers\AppBaseController;
use Response;
use Illuminate\Support\Facades\DB;

class RolesController extends AppBaseController
{
    /** @var  RolesRepository */
    private $rolesRepository;

    public function __construct(RolesRepository $rolesRepo)
    {
        $this->rolesRepository = $rolesRepo;
    }

    /**
     * Display a listing of the Roles.
     *
     * @param RolesDataTable $rolesDataTable
     * @return Response
     */
    public function index(RolesDataTable $rolesDataTable)
    {
        return $rolesDataTable->render('roles.index');
    }

    /**
     * Show the form for creating a new Roles.
     *
     * @return Response
     */
    public function create()
    {
        return view('roles.create');
    }

    /**
     * Store a newly created Roles in storage.
     *
     * @param CreateRolesRequest $request
     *
     * @return Response
     */
    public function store(CreateRolesRequest $request)
    {
        $input = $request->all();
        $input['slug'] = strtolower(str_replace(' ','_',$input['name']));
        
        $slug = \App\Models\Roles::withTrashed()->where('slug','=', $input['slug'])->first();
        if(!$slug){
            $roles = $this->rolesRepository->create($input);
            Flash::success(trans('custom.roles_saved'));
        }else{
            Flash::error(trans('custom.roles_already_exist'));
            return back();
        } 
        return redirect(route('roles.index'));
    }

    /**
     * Display the specified Roles.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function show($id)
    {
        $roles = $this->rolesRepository->findWithoutFail($id);

        if (empty($roles)) {
            Flash::error(trans('custom.roles_not_found'));

            return redirect(route('roles.index'));
        }

        return view('roles.show')->with('roles', $roles);
    }

    /**
     * Show the form for editing the specified Roles.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function edit($id)
    {
        $roles = $this->rolesRepository->findWithoutFail($id);

        if (empty($roles)) {
            Flash::error(trans('custom.roles_not_found'));

            return redirect(route('roles.index'));
        }

        return view('roles.edit')->with('roles', $roles);
    }

    /**
     * Update the specified Roles in storage.
     *
     * @param  int              $id
     * @param UpdateRolesRequest $request
     *
     * @return Response
     */
    public function update($id, UpdateRolesRequest $request)
    {
        $roles = $this->rolesRepository->findWithoutFail($id);
        
        if (empty($roles)) {
            Flash::error(trans('custom.roles_not_found'));

            return redirect(route('roles.index'));
        }
        
        $request['slug'] = strtolower(str_replace(' ','_',$request['name']));
        
        $roles = $this->rolesRepository->update($request->all(), $id);

        Flash::success(trans('custom.roles_updated'));

        return redirect(route('roles.index'));
    }

    /**
     * Remove the specified Roles from storage.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function destroy($id)
    {
        $roles = $this->rolesRepository->findWithoutFail($id);

        if (empty($roles)) {
            Flash::error(trans('custom.roles_not_found'));

            return redirect(route('roles.index'));
        }
        $this->rolesRepository->delete($id);
        
        DB::table('role_user')->where('role_id','=', $id)->delete();
        Flash::success(trans('custom.roles_deleted'));

        return redirect(route('roles.index'));
    }
}
