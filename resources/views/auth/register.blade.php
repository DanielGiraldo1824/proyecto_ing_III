<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>{{ trans('custom.tittle_app') }} | Registration Page</title>

    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">

    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

    <!-- Theme style -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.3.11/css/AdminLTE.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.3.11/css/skins/_all-skins.min.css">

    <!-- iCheck -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/skins/square/_all.css">

    <!-- Custom Skin -->
    <link rel="stylesheet" href="{{ url('/css/custom-skins.css') }}">
    
    <!-- Custom Favicon -->
    <link rel="icon" type="image/png" href="{{ url('/img/icon/48X48.png') }}" />
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition register-page">
<div class="register-box">
    <div class="register-logo">
        <img src="{{ url('/img/LOGO_BLANCO.png') }}" width="250" height="100" alt="Magic_ID_LOGO BLANCO_300"/>
    </div>

    <div class="register-box-body">
        <p class="login-box-msg">{{ trans('custom.register_message') }}</p>

        <form method="post" action="{{ url('/register') }}">

            {!! csrf_field() !!}

            <div class="form-group has-feedback{{ $errors->has('name') ? ' has-error' : '' }}">
                <input type="text" class="form-control" name="name" value="{{ old('name') }}" placeholder="{{ trans('custom.full_name') }}">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>

                @if ($errors->has('name'))
                    <span class="help-block">
                        <strong>{{ $errors->first('name') }}</strong>
                    </span>
                @endif
            </div>

            <div class="form-group has-feedback{{ $errors->has('email') ? ' has-error' : '' }}">
                <input type="email" class="form-control" name="email" value="{{ old('email') }}" placeholder="{{ trans('custom.email') }}">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>

                @if ($errors->has('email'))
                    <span class="help-block">
                        <strong>{{ $errors->first('email') }}</strong>
                    </span>
                @endif
            </div>

            <div class="form-group has-feedback{{ $errors->has('password') ? ' has-error' : '' }}">
                <input type="password" class="form-control" name="password" placeholder="{{ trans('custom.password') }}">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>

                @if ($errors->has('password'))
                    <span class="help-block">
                        <strong>{{ $errors->first('password') }}</strong>
                    </span>
                @endif
            </div>

            <div class="form-group has-feedback{{ $errors->has('password_confirmation') ? ' has-error' : '' }}">
                <input type="password" name="password_confirmation" class="form-control" placeholder="{{ trans('custom.password_confirm') }}">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>

                @if ($errors->has('password_confirmation'))
                    <span class="help-block">
                        <strong>{{ $errors->first('password_confirmation') }}</strong>
                    </span>
                @endif
            </div>
            <div class="form-group has-feedback">
                <input type="text" name="center_name" class="form-control" placeholder="{{ trans('custom.center_name') }}">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>

            </div>

            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label>
                            <input type="checkbox">{{ trans('custom.i_accept') }} <a href="#" data-toggle="modal" data-target="#myModal">{{ trans('custom.terms_conditions') }}</a>
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn gold-button btn-block btn-flat">{{ trans('custom.register') }}</button>
                </div>
                <!-- /.col -->
            </div>
        </form>

        <a href="{{ url('/login') }}" class="text-center">{{ trans('custom.already_account') }}</a>
    </div>
    <!-- /.form-box -->
</div>

<!-- Modal -->
<div class="modal modal-primary fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h2 class="modal-title" id="myModalLabel">{{ trans('custom.terms_conditions_title') }}</h2>
      </div>
      <div class="modal-body">
          <h4>1. Lorem ipsum dolor sit amet</h4>
       Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam egestas nibh risus, ut convallis lacus molestie quis. Donec ultricies dictum cursus. Suspendisse lacinia felis enim, nec consequat lorem auctor nec. Ut pretium libero vel mattis mollis. Maecenas suscipit molestie felis a consectetur. Nam rutrum lacus turpis, vel interdum leo tempus ut. Fusce non erat accumsan, pharetra mauris et, volutpat diam. Praesent ultricies fringilla libero at posuere. In condimentum augue ac tellus porttitor molestie. Vestibulum tincidunt id sem quis sagittis. Sed a maximus dui.
       <br>
       <h4>2. Lorem ipsum dolor sit amet</h4>
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam egestas nibh risus, ut convallis lacus molestie quis. Donec ultricies dictum cursus. Suspendisse lacinia felis enim, nec consequat lorem auctor nec. Ut pretium libero vel mattis mollis. Maecenas suscipit molestie felis a consectetur. Nam rutrum lacus turpis, vel interdum leo tempus ut. Fusce non erat accumsan, pharetra mauris et, volutpat diam. Praesent ultricies fringilla libero at posuere. In condimentum augue ac tellus porttitor molestie. Vestibulum tincidunt id sem quis sagittis. Sed a maximus dui.
      <br>
       <h4>3. Lorem ipsum dolor sit amet</h4>
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam egestas nibh risus, ut convallis lacus molestie quis. Donec ultricies dictum cursus. Suspendisse lacinia felis enim, nec consequat lorem auctor nec. Ut pretium libero vel mattis mollis. Maecenas suscipit molestie felis a consectetur. Nam rutrum lacus turpis, vel interdum leo tempus ut. Fusce non erat accumsan, pharetra mauris et, volutpat diam. Praesent ultricies fringilla libero at posuere. In condimentum augue ac tellus porttitor molestie. Vestibulum tincidunt id sem quis sagittis. Sed a maximus dui.
      <br>
       <h4>4. Lorem ipsum dolor sit amet</h4>
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam egestas nibh risus, ut convallis lacus molestie quis. Donec ultricies dictum cursus. Suspendisse lacinia felis enim, nec consequat lorem auctor nec. Ut pretium libero vel mattis mollis. Maecenas suscipit molestie felis a consectetur. Nam rutrum lacus turpis, vel interdum leo tempus ut. Fusce non erat accumsan, pharetra mauris et, volutpat diam. Praesent ultricies fringilla libero at posuere. In condimentum augue ac tellus porttitor molestie. Vestibulum tincidunt id sem quis sagittis. Sed a maximus dui.
      
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">{{ trans('custom.back') }}</button>
      </div>
    </div>
  </div>
</div>
<!-- /.register-box -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/icheck.min.js"></script>

<!-- AdminLTE App -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.3.11/js/app.min.js"></script>

<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>
</html>
