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
        @include('adminlte-templates::common.errors')
        <div class="box box-primary">

            <div class="box-body">
                <div class="row">
                    {!! Form::open(['route' => 'centers.store']) !!}

                        @include('centers.fields')

                    {!! Form::close() !!}
                </div>
            </div>
        </div>
    </div>
@endsection
