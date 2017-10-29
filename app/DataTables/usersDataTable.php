<?php

namespace App\DataTables;

use App\Models\users;
use Form;
use Yajra\Datatables\Services\DataTable;

class usersDataTable extends DataTable
{

    /**
     * @return \Illuminate\Http\JsonResponse
     */
    public function ajax()
    {
        $usersData = $this->datatables
            ->eloquent($this->query())->render(true)->getContent();
       
        $usersJSON = json_decode($usersData);
        
        return $this->datatables
            ->eloquent($this->query())
            ->addColumn('action', 'users.datatables_actions')
            ->editColumn('Centro', function ($usersJSON) {
            return \App\Models\centers::withTrashed()->find($usersJSON->centers_id)->name_center;
            })
            ->make(true);
    }

    /**
     * Get the query object to be processed by datatables.
     *
     * @return \Illuminate\Database\Query\Builder|\Illuminate\Database\Eloquent\Builder
     */
    public function query()
    {
        $users = users::query()->with('centers');

        return $this->applyScopes($users);
    }

    /**
     * Optional method if you want to use html builder.
     *
     * @return \Yajra\Datatables\Html\Builder
     */
    public function html()
    {
        return $this->builder()
            ->columns($this->getColumns())
            ->addAction(['width' => '10%'])
            ->ajax('')
            ->parameters([
                'dom' => 'Bfrtip',
                'scrollX' => true,
                'scrollY' => '50vh',
                'scrollCollapse' => true,
                'buttons' => [
                    'print',
                    'reset',
                    'reload',
                    [
                         'extend'  => 'collection',
                         'text'    => '<i class="fa fa-download"></i> Export',
                         'buttons' => [
                             'csv',
                             'excel',
                             'pdf',
                         ],
                    ],
                    'colvis'
                ]
            ]);
    }

    /**
     * Get columns.
     *
     * @return array
     */
    private function getColumns()
    {
        return [
            'Nombre Médico' => ['name' => 'name', 'data' => 'name'],
            'Correo Electrónico' => ['name' => 'email', 'data' => 'email'],
            'Centro' => ['centers.name_center' => 'email', 'data' => 'centers.name_center', 'searchable' => true]
//            'Contraseña' => ['name' => 'password', 'data' => 'password'],
//            'Remember Token' => ['name' => 'remember_token', 'data' => 'remember_token']
        ];
    }

    /**
     * Get filename for export.
     *
     * @return string
     */
    protected function filename()
    {
        return 'users';
    }
}
