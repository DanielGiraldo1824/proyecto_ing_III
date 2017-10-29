<!-- Name Field -->
<!--<div class="form-group col-sm-6">
    <strong>{{ trans('custom.full_name') }}:</strong>
    {!! Form::text('name', null, ['class' => 'form-control']) !!}
</div>-->

<!-- Email Field -->
<!-- <div class="form-group col-sm-6">
    <strong>{{ trans('custom.full_name') }}:</strong>
    {!! Form::email('email', null, ['class' => 'form-control']) !!}
</div>-->


<!-- Password Field -->
<!--<div class="form-group col-sm-6">
    <strong>{{ trans('custom.password') }}:</strong>
    {!! Form::password('password', ['class' => 'form-control']) !!}
</div>-->

<div class="form-group col-sm-6 ">
    <strong>{{ trans('custom.full_name') }}:</strong>
    <div class="input-group">
        <span class="input-group-addon" id="sizing-addon2"><i class="fa fa-user "></i></span>
        <input type="text" name="name" class="form-control" required aria-describedby="sizing-addon2" value ="{{ (! empty($users)? $users->name: '' )}}" >
    </div>
</div>

<div class="form-group col-sm-6 ">
    <strong>{{ trans('custom.email') }}:</strong>
    <div class="input-group">
        <span class="input-group-addon" id="sizing-addon2"><i class="fa fa-envelope "></i></span>
        <input type="email" name="email" class="form-control" required aria-describedby="sizing-addon2" value ="{{ (! empty($users)? $users->email: '' )}}">
    </div>
</div>

<div class="form-group col-sm-6 ">
    <strong>{{ trans('custom.password') }}:</strong>
    <div class="input-group">
        <span class="input-group-addon" id="sizing-addon2"><i class="fa  fa-key "></i></span>
        <input type="password" name="password" class="form-control" minlength="6" required aria-describedby="sizing-addon2" >
    </div>
</div>

<div class="form-group col-sm-6 ">
    <strong>{{ trans('custom.password_confirm') }}:</strong>
    <div class="input-group">
        <span class="input-group-addon" id="sizing-addon2"><i class="fa  fa-key "></i></span>
        <input type="password" name="password_confirmation" class="form-control" minlength="6" required aria-describedby="sizing-addon2">
    </div>
</div>

<div  class="form-group col-sm-6 " >
    <strong>{{ trans('custom.role') }}:</strong>
    <select multiple class="form-control col-sm-6" name="role[]" id="roleSelect" >
            @foreach ($roles as $rol)
                <option value="{{ $rol['id'] }}" @if($rol['selected'] == true)selected="selected"@endif> {{ $rol['name'] }}</option>
            @endforeach
    </select>
</div>

<div  class="form-group col-sm-6 " >
    <strong>{{ trans('custom.center') }}:</strong>
    <select class="form-control col-sm-6" name="center" >
            @foreach ($centers as $center)
                <option value="{{ $center['id'] }}" > {{ $center['name_center'] }}</option>
            @endforeach
    </select>
</div>
<!-- Remember Token Field 
<!--<div class="form-group col-sm-6">
    {!! Form::label('remember_token', 'Remember Token:') !!}
    {!! Form::text('remember_token', null, ['class' => 'form-control']) !!}
</div>-->

<!-- Submit Field -->
<div class="form-group col-sm-12">
    <button type="submit" class="btn btn-primary">
        <i class="fa fa-floppy-o "></i>
        {{ trans('custom.save') }}
    </button>
    <a href="{!! route('users.index') !!}" class="btn btn-default">
        <i class="fa  fa-close "></i>
        {{ trans('custom.cancel') }}
    </a>
</div>

