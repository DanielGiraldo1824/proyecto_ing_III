<!-- Name Field 
<div class="form-group col-sm-6">
    {!! Form::label('name', 'Name:') !!}
    {!! Form::text('name', null, ['class' => 'form-control']) !!}
</div>
-->

<div class="form-group col-sm-6 ">
    <strong>{{ trans('custom.name') }}:</strong>
    <div class="input-group">
        <span class="input-group-addon" id="sizing-addon2"><i class="fa fa-cog"></i></span>
        <input type="text" name="name" class="form-control" required maxlength="45" aria-describedby="sizing-addon2" value ="{{ (! empty($roles)? $roles->name: '' )}}" >
    </div>
</div>

<!-- Slug Field 
<div class="form-group col-sm-6">
    {!! Form::label('slug', 'Slug:') !!}
    {!! Form::text('slug', null, ['class' => 'form-control']) !!}
</div>
-->

<!-- Description Field 
<div class="form-group col-sm-6">
    {!! Form::label('description', 'Description:') !!}
    {!! Form::text('description', null, ['class' => 'form-control']) !!}
</div>
-->

<div class="form-group col-sm-6 ">
    <strong>{{ trans('custom.description') }}:</strong>
    <div class="input-group">
        <span class="input-group-addon" id="sizing-addon2"><i class="fa fa-indent"></i></span>
        <input type="text" name="description" class="form-control" required maxlength="255" aria-describedby="sizing-addon2" value ="{{ (! empty($roles)? $roles->description: '' )}}" >
    </div>
</div>

<!-- Group Field 
<div class="form-group col-sm-6">
    {!! Form::label('group', 'Group:') !!}
    {!! Form::text('group', null, ['class' => 'form-control']) !!}
</div>
-->
<div class="form-group col-sm-6 ">
    <strong>{{ trans('custom.group') }}:</strong>
    <div class="input-group">
        <span class="input-group-addon" id="sizing-addon2"><i class="fa fa-cogs"></i></span>
        <input type="text" name="group" class="form-control" required maxlength="45" aria-describedby="sizing-addon2" value ="{{ (! empty($roles)? $roles->group: '' )}}" >
    </div>
</div>

<!-- Submit Field -->
<div class="form-group col-sm-12">
    <button type="submit" class="btn btn-primary">
        <i class="fa fa-floppy-o "></i>
        {{ trans('custom.save') }}
    </button>
    <a href="{!! route('roles.index') !!}" class="btn btn-default">
        <i class="fa  fa-close "></i>
        {{ trans('custom.cancel') }}
    </a>
    
</div>
