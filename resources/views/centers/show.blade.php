@extends('layouts.app')

@section('content')
    <section class="content-header">
        <h1>
             <i class="fa fa-building"></i>
             {{ trans('custom.centers') }}
        </h1>
        <ol class="breadcrumb">
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
    <div class="content">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row" style="padding-left: 20px">
                    @include('centers.show_fields')
                    <a href="{!! route('centers.index') !!}" class="btn btn-default"> 
                        <i class="fa fa-reply"></i> 
                        {{ trans('custom.back') }}
                    </a>
                </div>
            </div>
        </div>
    </div>
@endsection
