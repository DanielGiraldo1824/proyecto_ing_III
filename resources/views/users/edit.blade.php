@extends('layouts.app')

@section('content')
    <section class="content-header">
        
        <h1>
            <i class="fa fa-user-md "></i>
            {{ trans('custom.users') }}
        </h1>
        <ol class="breadcrumb" >
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
   <div class="content">
       @include('adminlte-templates::common.errors')
       <div class="box box-primary">
           <div class="box-body">
               <div class="row">
                   {!! Form::model($users, ['route' => ['users.update', $users->id], 'method' => 'patch', 'id'=>'UserEditForm']) !!}

                        @include('users.fields')

                   {!! Form::close() !!}
               </div>
           </div>
       </div>
   </div>
<!-- Modal -->
<div class="modal modal-warning fade" id="SelectAlert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h2 class="modal-title" id="myModalLabel">Rol no Seleccionado</h2>
      </div>
      <div class="modal-body">
          Por favor seleccione uno o varios roles para asignarle al usuario.
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal">{{ trans('custom.back') }}</button>
      </div>
    </div>
  </div>
</div>
@endsection
@push('scripts')
<script type="text/javascript">
    
    $('#UserEditForm').submit(function() {
        if($( "#roleSelect" ).val() == ""){
            $('#SelectAlert').modal('show');
            return false;
        }else{
            return true;
        }
            
    });
</script>
@endpush