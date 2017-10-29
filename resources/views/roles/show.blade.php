@extends('layouts.app')

@section('content')
    <section class="content-header">
        <h1>
            <i class="fa fa-gear"></i>
           {{ trans('custom.roles') }}
        </h1>
        <ol class="breadcrumb">
            <li>
                <a href="{!! route('home.index') !!}">
                    <i class="fa fa-dashboard"></i> 
                    {{ trans('custom.home') }}
                </a>
            </li>
            <li class="active">
                {{ trans('custom.roles') }}
            </li>
        </ol>
    </section>
    <div class="content">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row" style="padding-left: 20px">
                    @include('roles.show_fields')
                    <a href="{!! route('roles.index') !!}" class="btn btn-default">
                        <i class="fa fa-reply"></i> 
                        {{ trans('custom.back') }}
                    </a>
                </div>
            </div>
        </div>
    </div>
@endsection
