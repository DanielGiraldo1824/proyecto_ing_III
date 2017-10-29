@extends('layouts.app')
{!! Charts::assets() !!}
@section('content')
<div class="container-fluid">
    <div class="row">
        <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Dashboard
        <small>{{ trans('custom.control_panel') }}</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> {{ trans('custom.home') }}</a></li>
        <li class="active">Dashboard</li>
      </ol>
    </section>

        <section class="content">
            @include('flash::message')
      <!-- Small boxes (Stat box) -->
      <div class="row">
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-aqua">
            <div class="inner">
             <h3>{{ $users_count }}</h3>

              <p>{{ trans('custom.users') }}</p>
            </div>
            <div class="icon">
              <i class="ion ion-person-stalker"></i>
            </div>
            <a href="{!! route('users.index') !!}" class="small-box-footer">{{ trans('custom.more_info') }}<i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-green">
            <div class="inner">
               <h3>{{ $patients_count }}</h3>

              <p>{{ trans('custom.patients') }}</p>
            </div>
            <div class="icon">
              <i class="ion ion-person"></i>
            </div>
            <a href="{!! route('centers.index') !!}" class="small-box-footer">{{ trans('custom.more_info') }}<i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-yellow">
            <div class="inner">
              <h3>{{ $procedures_count }}</h3> 

              <p>{{ trans('custom.procedures') }}</p>
            </div>
            <div class="icon">
              <i class="ion ion-clipboard"></i>
            </div>
            <a href="{!! route('audits.index') !!}" class="small-box-footer">{{ trans('custom.more_info') }}<i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-red">
            <div class="inner">
                <h3>{{ $Histories_count }}</h3> 

              <p>{{ trans('custom.clinical_history') }}</p>
            </div>
            <div class="icon">
              <i class="ion ion-medkit"></i>
            </div>
            <a href="{!! route('roles.index') !!}" class="small-box-footer">{{ trans('custom.more_info') }}<i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
      </div>
      <!-- /.row -->
      <!-- Main row -->
      <div class="row">
        <!-- Left col -->
        <section class="col-lg-6 connectedSortable">
          <!-- Custom tabs (Charts with tabs)-->
          <div class="nav-tabs-custom">
            <!-- Tabs within a box -->
            <ul class="nav nav-tabs pull-right">
              <li class="active"><a href="#revenue-chart" data-toggle="tab">{{ trans('custom.patients') }}</a></li>
              <li><a href="#sales-chart" data-toggle="tab">{{ trans('custom.procedures') }}</a></li>
              <li class="pull-left header"><i class="fa fa-bar-chart"></i> {{ trans('custom.quick_report') }}</li>
            </ul>
            <div class="tab-content no-padding">
              <!-- Morris chart - Sales -->
              <div class="chart tab-pane active" id="revenue-chart" style="position: relative; height: 300px;">
                  {!! $chart->render() !!}
              </div>
              <div class="chart tab-pane" id="sales-chart" style="position: relative; height: 300px;">
                  {!! $chart_2->render() !!}
              </div>
            </div>
          </div>
          <!-- /.nav-tabs-custom -->

        </section>
        <section class="col-lg-6 connectedSortable">
           <!-- quick email widget -->
          <div class="box box-info">
            <div class="box-header">
              <i class="fa fa-envelope"></i>

              <h3 class="box-title">Quick Email</h3>
              
              <!-- /. tools -->
            </div>
            <div class="box-body">
              <form action="{{ url('/home') }}" method="post">
                  {!! csrf_field() !!}
                <div class="form-group">
                  <input type="email" class="form-control" name="emailto" placeholder="Email to:">
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" name="subject" placeholder="Subject">
                </div>
                <div>
                    <textarea class="textarea" placeholder="Message" name="content"
                            style="width: 100%; height: 125px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
                </div>
                <div class="box-footer clearfix">
                  <button type="button" class="pull-left btn btn-default" id="sendEmail">Limpiar
                    <i class="fa fa-arrow-circle-right"></i></button>
                    <button type="submit" class="pull-right btn btn-default" id="sendEmail">Enviar
                    <i class="fa fa-arrow-circle-right"></i></button>
                </div>
              </form>
            </div>
            
          </div> 
        </section>
    </div>
</div>
@endsection
