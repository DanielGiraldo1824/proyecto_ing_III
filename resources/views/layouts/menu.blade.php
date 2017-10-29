<li class="{{ Request::is('home*') ? 'active' : '' }}">
    <a href="{!! route('home.index') !!}"><i class="fa fa-home"></i><span>{{ trans('custom.home') }}</span></a>
</li>

<li class="{{ Request::is('users*') ? 'active' : '' }}">
    <a href="{!! route('users.index') !!}"><i class="fa fa-user-md"></i><span>{{ trans('custom.users') }}</span></a>
</li>

<li class="{{ Request::is('centers*') ? 'active' : '' }}">
    <a href="{!! route('centers.index') !!}"><i class="fa fa-building"></i><span>{{ trans('custom.centers') }}</span></a>
</li>

<li class="{{ Request::is('roles*') ? 'active' : '' }}">
    <a href="{!! route('roles.index') !!}"><i class="fa fa-gear"></i><span>{{ trans('custom.roles') }}</span></a>
</li>

<li class="{{ Request::is('audits*') ? 'active' : '' }}">
    <a href="{!! route('audits.index') !!}"><i class="fa fa-clock-o "></i><span>{{ trans('custom.history') }}</span></a>
</li>

