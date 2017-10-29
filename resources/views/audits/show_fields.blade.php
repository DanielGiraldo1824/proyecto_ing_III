<div class="row">
    <div class="panel panel-primary col-md-3">
        <div class="panel-heading"> 
            <h3 class="panel-title "><i class="fa  fa-list-alt"></i> {{trans('custom.details')}}</h3> 
        </div>
        <div class="panel-body"> 
            <div class="table-striped">
                <table class="table table-striped"> 
                    <tbody> 
                        <tr> 
                            <th scope="row">{{ trans('custom.user') }}</th> 
                            <td>{!! $audit->userName !!}</td>
                        </tr>
                        <tr> 
                            <th scope="row">{{ trans('custom.module') }}</th> 
                            <td>{!! $audit->auditable_type !!}</td>
                        </tr>
                        <tr> 
                            <th scope="row">{{ trans('custom.event') }}</th> 
                             @if($audit->event == 'created')
                             <td><h4> <span class="label label-success">Creado</span></h4></td>
                            @elseif($audit->event == 'updated')
                                <td><h4> <span class="label label-warning">Actualizado</span></h4></td>
                            @elseif($audit->event == 'deleted')
                                <td><h4> <span class="label label-danger">Eliminado</span></h4></td>
                            @endif
                            
                        </tr>
                        <tr> 
                            <th scope="row">{{ trans('custom.date') }}</th> 
                            <td>{!! $audit->created_at !!}</td>
                        </tr>
                    </tbody> 
                </table> 
            </div>
        </div>
    </div> 
    @if(count($oldValues) != 0)
    <div class="col-md-4 col-md-offset-1">
        <div class="panel panel-primary ">
            <div class="panel-heading"> 
                <h3 class="panel-title"><i class="fa   fa-caret-square-o-down"></i> {{trans('custom.old_values')}}</h3> 
            </div>
            <div class="panel-body"> 
                <div class="table-striped">
                    <table class="table"> 
                        <tbody> 
                            @foreach($oldValues as $value)
                                <tr>
                                    <th scope="row">{!! str_replace('_',' ',ucwords ($value['columna'])) !!}</th> 
                                    <td>{!! $value['valor'] !!}</td>
                                </tr>
                            @endforeach
                            <tr>
                                <th scope="row"></th>
                                <td>
                                    @if($audit->event == 'deleted')
                                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#UndoModal">
                                            <i class="fa  fa-history"></i>
                                            Deshacer
                                        </button>
                                    @endif
                                </td>
                            </tr>    
                        </tbody> 
                        
                    </table> 
                </div>
            </div>
        </div>
    </div>
    @endif
    @if(count($newValues) != 0)
    <div class="col-md-4 col-md-offset-1">
        <div class="panel panel-primary ">
            <div class="panel-heading"> 
                <h3 class="panel-title"><i class="fa fa-caret-square-o-up"></i> {{trans('custom.new_values')}}</h3> 
            </div>
            <div class="panel-body"> 
                <div class="table-striped">
                    <table class="table"> 
                        <tbody> 
                            @foreach($newValues as $values)
                                <tr>
                                    <th scope="row">{!! str_replace('_',' ',ucwords ($values['columna'])) !!}</th> 
                                    <td>{!! $values['valor'] !!}</td>
                                </tr>
                            @endforeach
                        </tbody> 
                    </table> 
                </div>
            </div>
        </div>
    </div>
    @endif
</div>

<!-- Modal -->
<div class="modal modal-default fade" id="UndoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="myModalLabel"><i class="fa fa-exclamation-triangle" style="color:orange;"></i> ¿Desea restaurar el elemento seleccionado?</h3>
      </div>
      <div class="modal-footer">
          <div class="col-md-4 col-md-offset-4">
                <a href="{{ URL::to('audits-undo/'.$oldValues[0]['valor'].'/'.$audit->auditable_type.'/'.$audit->id) }}" class="btn btn-primary"> 
                 <i class="fa  fa-history" ></i>
                 {{ trans('custom.undo') }}
                </a>
                <button type="button" class="btn btn-default" data-dismiss="modal">{{ trans('custom.back') }}</button>
          </div>
      </div>
    </div>
  </div>
</div>


