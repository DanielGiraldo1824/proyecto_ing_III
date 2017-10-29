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
        <div class="box box-primary">
            <div class="box-body">
                <div class="row" style="padding-left: 20px">
                    @include('audits.show_fields')
                    <a href="{!! route('audits.index') !!}" class="btn btn-default"> 
                        <i class="fa fa-reply"></i> 
                        {{ trans('custom.back') }}
                    </a>
                </div>
                
            </div>
            
        </div>
    </div>

@endsection
