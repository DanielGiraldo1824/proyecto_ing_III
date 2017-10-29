@extends('layouts.app')

@section('content')
    <section class="content-header">
        
        <h1 class="pull-left">
            <i class="fa fa-user-md "></i>
            {{ trans('custom.users') }}
        </h1>
        <ol class="breadcrumb">
            <li>
                <a href="{!! route('home.index') !!}">
                    <i class="fa fa-dashboard"></i> 
                    {{ trans('custom.home') }}
                </a>
            </li>
            <li class="active">
                {{ trans('custom.users') }}
            </li>
        </ol>
    </section>
<br>
    <div class="content">
        <div class="box box-primary">
            <div class="box-body">
                <div class="row" style="padding-left: 20px">
                    @include('users.show_fields')
                    <a href="{!! route('users.index') !!}" class="btn btn-default">
                        <i class="fa fa-reply"></i> 
                        {{ trans('custom.back') }}
                    </a>
                </div>
            </div>
        </div>
    </div>
@endsection
