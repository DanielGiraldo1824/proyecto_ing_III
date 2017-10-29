@section('css')
    @include('layouts.datatables_css')
@endsection

{!! $dataTable->table(['width' => '100%','id'=>'centers_table'],true) !!}

@section('scripts')
    @include('layouts.datatables_js')
    {!! $dataTable->scripts() !!}
@endsection