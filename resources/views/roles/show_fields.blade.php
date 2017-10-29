
<!-- Name Field -->
<div class="form-group">
    <strong>{{ trans('custom.role') }}:</strong>
    <p>{!! $roles->name !!}</p>
</div>

<!-- Description Field -->
<div class="form-group">
    <strong>{{ trans('custom.description') }}:</strong>
    @if($roles->description == "")
        <p>Ninguno</p>
    @else
        <p>{!! $roles->description !!}</p>
    @endif
</div>

<!-- Group Field -->
<div class="form-group">
    <strong>{{ trans('custom.group') }}:</strong>
    <p>{!! $roles->group !!}</p>
</div>


