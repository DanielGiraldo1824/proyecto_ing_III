<!-- Name City Field -->
<div class="form-group col-sm-6">
    {!! Form::label('name_city', 'Name City:') !!}
    {!! Form::text('name_city', null, ['class' => 'form-control']) !!}
</div>

<!-- Submit Field -->
<div class="form-group col-sm-12">
    {!! Form::submit('Save', ['class' => 'btn btn-primary']) !!}
    <a href="{!! route('cities.index') !!}" class="btn btn-default">Cancel</a>
</div>
