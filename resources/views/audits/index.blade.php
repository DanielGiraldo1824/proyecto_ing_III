@extends('layouts.app')

@section('content')
    <section class="content-header">
        <h1 class="pull-left">
            <i class="fa fa-clock-o"></i>
            {{ trans('custom.history') }}
        </h1>
        <ol class="breadcrumb" style="position:initial !important; float:left !important; margin-top:-2px;margin-left: 20px;">
            <li>
                <a href="{!! route('home.index') !!}">
                    <i class="fa fa-dashboard"></i> 
                    {{ trans('custom.home') }}
                </a>
            </li>
            <li class="active">
                {{ trans('custom.history') }}
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
                    @include('audits.table')
            </div>
        </div>
    </div>
@endsection
@push('scripts')
<script type="text/javascript">
        $('#audits_table').dataTable().yadcf([
                    {   column_number : 0,
                        filter_type: "text",
                        filter_default_label: ""
                    },
                    {   column_number : 2,
                        filter_type: "text",
                        filter_default_label: ""
                    },
                    {   column_number : 3,
                        filter_type: "date",
                        date_format: "yyyy-mm-dd",
                        filter_reset_button_text:"x",
                        filter_default_label: "",
                    },
                  ]);
        $('#audits_table').addClass("cell-border hover");          
    

</script>
@endpush

