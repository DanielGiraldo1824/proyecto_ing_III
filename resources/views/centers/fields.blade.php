<!-- Name Field -->
<!--<div class="form-group col-sm-6">
    <strong>{{ trans('custom.name') }}:</strong>
    {!! Form::text('name', null, ['class' => 'form-control','required','maxlength'=>45]) !!}
</div>-->

<div class="form-group col-sm-6 ">
    <strong>{{ trans('custom.name') }}:</strong>
    <div class="input-group">
        <span class="input-group-addon" id="sizing-addon2"><i class="fa fa-building "></i></span>
        <input type="text" name="name_center" class="form-control" required maxlength="45" aria-describedby="sizing-addon2" value ="{{ (! empty($centers)? $centers->name_center: '' )}}" >
    </div>
</div>

<!-- Submit Field -->
<div class="form-group col-sm-12">
    <button type="submit" class="btn btn-primary">
        <i class="fa fa-floppy-o "></i>
        {{ trans('custom.save') }}
    </button>
    <a href="{!! route('centers.index') !!}" class="btn btn-default">
        <i class="fa  fa-close "></i>
        {{ trans('custom.cancel') }}
    </a>
</div>
