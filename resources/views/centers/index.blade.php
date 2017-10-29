@extends('layouts.app')

@section('content')
    <section class="content-header">
        <h1 class="pull-left">
            <i class="fa fa-building"></i>
            {{ trans('custom.centers') }}
        </h1>
        <h1 class="text-center">
           <a class="btn btn-primary pull-right" style="margin-top: -10px;margin-bottom: 5px" href="{!! route('centers.create') !!}">
               <i class="fa fa-fw fa-plus-square"></i>
               {{ trans('custom.add_new') }}
           </a>
        </h1>
        <ol class="breadcrumb" style="position:initial !important; float:left !important; margin-top:-2px;margin-left: 20px;">
            <li>
                <a href="{!! route('home.index') !!}">
                    <i class="fa fa-dashboard"></i> 
                    {{ trans('custom.home') }}
                </a>
            </li>
            <li class="active">
                {{ trans('custom.centers') }}
            </li>
        </ol>
    </section>
<br>
    <div class="content">
        <div class="clearfix"></div>

        @include('flash::message')

        <div class="clearfix"></div>
        <div class="box box-primary">
            <div class="box-body">
                    @include('centers.table')
            </div>
        </div>
    </div>
@endsection
@push('scripts')
<script type="text/javascript">
        $('#centers_table').dataTable().yadcf([
                    {   column_number : 0,
                        filter_type: "text",
                        filter_default_label: ""
                    }
                  ]);
        $('#centers_table').addClass("cell-border hover");          
    

</script>
@endpush

