<!-- Id Field -->
<!--<div class="form-group">
    {!! Form::label('id', 'Id:') !!}
    <p>{!! $users->id !!}</p>
</div>-->

<!-- Name Field -->
<div class="form-group">
    <strong>{{ trans('custom.full_name') }}:</strong>
    <p>{!! $users->name !!}</p>
</div>

<!-- Email Field -->
<div class="form-group">
    <strong>{{ trans('custom.email') }}:</strong>
    <p>{!! $users->email !!}</p>
</div>

<!-- Center Field -->
<div class="form-group">
    <strong>{{ trans('custom.center') }}:</strong>
    <p>{!! $center !!}</p>
</div>

<div class="form-group">
    <strong>{{ trans('custom.role') }}:</strong>
    @if(count($roleNameList) == 0)
        <h4> <span class="label label-default">Ninguno</span></h4>
    @endif
    @foreach($roleNameList as $role)
        <h4> <span class="label label-primary">{!! $role !!}</span></h4>
    @endforeach
</div>

<!-- Password Field -->
<!--<div class="form-group">
    <strong>{{ trans('custom.password') }}:</strong>
    <p>{!! $users->password !!}</p>
</div>-->

<!-- Remember Token Field -->
<!--<div class="form-group">
    {!! Form::label('remember_token', 'Remember Token:') !!}
    <p>{!! $users->remember_token !!}</p>
</div>-->

<!-- Created At Field -->
<!--<div class="form-group">
    {!! Form::label('created_at', 'Created At:') !!}
    <p>{!! $users->created_at !!}</p>
</div>-->

<!-- Updated At Field -->
<!--<div class="form-group">
    {!! Form::label('updated_at', 'Updated At:') !!}
    <p>{!! $users->updated_at !!}</p>
</div>-->

<!-- Deleted At Field -->
<!--<div class="form-group">
    {!! Form::label('deleted_at', 'Deleted At:') !!}
    <p>{!! $users->deleted_at !!}</p>
</div>-->

