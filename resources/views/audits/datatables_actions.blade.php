{!! Form::open(['route' => ['audits.destroy', $id], 'method' => 'delete']) !!}
<div class='btn-group'>
    <a href="{{ route('audits.show', $id) }}" class='btn btn-primary btn-xs'>
        <i class="glyphicon glyphicon-eye-open"></i>
    </a>
    
</div>
{!! Form::close() !!}
