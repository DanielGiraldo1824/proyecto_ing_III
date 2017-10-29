<?php

namespace App\Http\Controllers;

use App\DataTables\AuditDataTable;
use App\Http\Requests;
use App\Http\Requests\CreateAuditRequest;
use App\Http\Requests\UpdateAuditRequest;
use App\Repositories\AuditRepository;
use Flash;
use App\Http\Controllers\AppBaseController;
use Response;
use App\Models\users;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class AuditController extends AppBaseController
{
    /** @var  AuditRepository */
    private $auditRepository;

    public function __construct(AuditRepository $auditRepo)
    {
        $this->auditRepository = $auditRepo;
    }

    /**
     * Display a listing of the Audit.
     *
     * @param AuditDataTable $auditDataTable
     * @return Response
     */
    public function index(AuditDataTable $auditDataTable)
    {
        return $auditDataTable->render('audits.index');
    }

    /**
     * Show the form for creating a new Audit.
     *
     * @return Response
     */
    public function create()
    {
        return view('audits.create');
    }

    /**
     * Store a newly created Audit in storage.
     *
     * @param CreateAuditRequest $request
     *
     * @return Response
     */
    public function store(CreateAuditRequest $request)
    {
        $input = $request->all();

        $audit = $this->auditRepository->create($input);

        Flash::success('Audit saved successfully.');

        return redirect(route('audits.index'));
    }

    /**
     * Display the specified Audit.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function show($id)
    {
        $audit = $this->auditRepository->findWithoutFail($id);

        if (empty($audit)) {
            Flash::error(trans('custom.audit_not_found'));

            return redirect(route('audits.index'));
        }
        $audit['auditable_type'] = str_replace("App","",$audit['auditable_type']);
        $audit['auditable_type'] = str_replace("\Models","",$audit['auditable_type']);
        $audit['auditable_type'] = stripslashes($audit["auditable_type"]);
        
        if($audit['user_id'] != ""){
          $audit['userName'] = users::withTrashed()->find($audit['user_id'])->name;
        }
        
        $oldValues = [];
        $i = 0;
        if($audit['old_values'] != ""){
            foreach (json_decode($audit['old_values']) as $nombre => $valor) {
                if($nombre != 'password' && $nombre != 'remember_token' ){
                    $oldValues[$i]['columna'] = $nombre;
                    $oldValues[$i]['valor'] = $valor;
                    $i++;
                }
            }
        }
        
        $newValues = [];
        $j = 0;
        if($audit['new_values'] != ""){
            foreach (json_decode($audit['new_values']) as $nombre => $valor) {
                if($nombre != 'password'){
                    $newValues[$j]['columna'] = $nombre;
                    $newValues[$j]['valor'] = $valor;
                    $j++;
                }
            }
        }
        return view('audits.show')->with('audit', $audit)
                                  ->with('oldValues', $oldValues)
                                  ->with('newValues', $newValues);
    }

    /**
     * Show the form for editing the specified Audit.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function edit($id)
    {
        $audit = $this->auditRepository->findWithoutFail($id);

        if (empty($audit)) {
            Flash::error('Audit not found');

            return redirect(route('audits.index'));
        }

        return view('audits.edit')->with('audit', $audit);
    }

    /**
     * Update the specified Audit in storage.
     *
     * @param  int              $id
     * @param UpdateAuditRequest $request
     *
     * @return Response
     */
    public function update($id, UpdateAuditRequest $request)
    {
        $audit = $this->auditRepository->findWithoutFail($id);

        if (empty($audit)) {
            Flash::error('Audit not found');

            return redirect(route('audits.index'));
        }

        $audit = $this->auditRepository->update($request->all(), $id);

        Flash::success('Audit updated successfully.');

        return redirect(route('audits.index'));
    }

    /**
     * Remove the specified Audit from storage.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function destroy($id)
    {
        $audit = $this->auditRepository->findWithoutFail($id);

        if (empty($audit)) {
            Flash::error('Audit not found');

            return redirect(route('audits.index'));
        }

        $this->auditRepository->delete($id);

        Flash::success('Audit deleted successfully.');

        return redirect(route('audits.index'));
    }
    
    
    public function undoDelete($id , $module, $audit){
        switch($module){
            case 'users':
                $result = DB::table('users')->where('id', $id)
                                            ->update(['deleted_at' => null]);
                break;
            case 'centers':
                $result = DB::table('centers')->where('id', $id)
                                            ->update(['deleted_at' => null]);
                break;
            case 'eps':
                $result = DB::table('eps')->where('id', $id)
                                            ->update(['deleted_at' => null]);
                break;
            case 'patient':
                $result = DB::table('patient')->where('id', $id)
                                            ->update(['deleted_at' => null]);
                break;
            case 'procedures':
                $result = DB::table('procedures')->where('id', $id)
                                            ->update(['deleted_at' => null]);
                break;
            case 'roles':
                $result = DB::table('procedures')->where('id', $id)
                                            ->update(['deleted_at' => null]);
                break;
        }
        
        $result = DB::table('audits')->where('id','=', $audit)->delete();
        return redirect(route('audits.index'));
    }
}
