<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>{{ trans('custom.tittle_app') }} | Login</title>

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

    <!-- iCheck -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.3.11/css/skins/_all-skins.min.css">

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
<body class="hold-transition login-page">
<div class="login-box">
    
    <div class="login-logo">
        <img src="{{ url('/img/LOGO_BLANCO.png') }}" width="250" height="100" alt="LOGO_BLANCO"/>
    </div>

    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">{{ trans('custom.login_message') }} </p>

        <form method="post" action="{{ url('/login') }}">
            {!! csrf_field() !!}

            <div class="form-group has-feedback {{ $errors->has('email') ? ' has-error' : '' }}">
                <input type="email" class="form-control" name="email" value="{{ old('email') }}" placeholder="{{ trans('custom.email') }}" required>
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                @if ($errors->has('email'))
                    <span class="help-block">
                    <strong>{{ $errors->first('email') }}</strong>
                </span>
                @endif
            </div>

            <div class="form-group has-feedback{{ $errors->has('password') ? ' has-error' : '' }}">
                <input type="password" class="form-control" placeholder="{{ trans('custom.password') }}" name="password" required>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                @if ($errors->has('password'))
                    <span class="help-block">
                    <strong>{{ $errors->first('password') }}</strong>
                </span>
                @endif

            </div>
        <div class="row">
        @if($totalUsers == 0)
            <div class="col-xs-6">
        @else
            <div class="col-xs-12"> 
        @endif
                    <button type="submit" class="btn gold-button btn-block btn-flat"> 
                        <i class="fa fa-chevron-circle-right"></i>
                        {{ trans('custom.login') }}
                    </button>                  
                </div>
                <!--<div class="col-xs-4">
                    <div class="checkbox icheck">
                        <label>
                            <input type="checkbox" name="remember"> Remember Me
                        </label>
                    </div>
                </div>-->
                
                <!-- /.col -->
                @if($totalUsers == 0)
                <div class="col-xs-6">
                    
                    <a href="{{ url('/register') }}" class="gold-button btn btn-block btn-flat">
                        <i class="fa  fa-user-plus"></i>
                            {{ trans('custom.register') }}
                    </a>
                </div>
                @endif
                <!-- /.col -->
            </div>
        </form>
        <br>
        <a href="{{ url('/password/reset') }}">
            <i class="fa fa-lock"></i>
             {{ trans('custom.forgot_password') }}
        </a><br>
        <!--<a href="{{ url('/register') }}" class="text-center">Register a new membership</a>-->

    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

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
