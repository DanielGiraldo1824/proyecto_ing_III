<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>{{ trans('custom.tittle_app') }} | Inicio</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.3.11/css/AdminLTE.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.3.11/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/skins/square/_all.css">

    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Custom Skin -->
    <link rel="stylesheet" href="{{ url('/css/custom-skins.css') }}">
    <!-- Custom Favicon -->
    <link rel="icon" type="image/png" href="{{ url('/img/icon/48X48.png') }}" />
    <!-- jvectormap -->
    <link rel="stylesheet" href="{{ url('/css/jquery-jvectormap.css') }}">
    
    <!-- Datatables Material Design -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/dataTables.material.min.css">
    
    <!--Jquery UI-->
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
    @yield('css')
</head>

<body class="skin-magic sidebar-mini">
@if (!Auth::guest())
    <div class="wrapper">
        <!-- Main Header -->
        <header class="main-header">

            <!-- Logo -->
            <a href="#" class="logo">
                <img src="{{ url('/img/LOGO_BLANCO.png')}}" id="bigLogo"width="116" height="50" alt="LOGO BLANCO">
                <img src="{{ url('/img/icon/48X48.png') }}" id="smallLogo" class="hidden" width="40" height="40" style= "margin-left: -8px;    margin-top: 7px;" alt="LOGO BLANCO"/>
            </a>

            <!-- Header Navbar -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button" id ="sidebar_button_toggle">
                    <span class="sr-only">Toggle navigation</span>
                </a>
                <!-- Navbar Right Menu -->
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                        <!-- User Account Menu -->
                        <li class="dropdown user user-menu">
                            <!-- Menu Toggle Button -->
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <!-- The user image in the navbar-->
                                <img src="{{ url('/img/usuario-01.png') }}"
                                     class="user-image" alt="User Image"/>
                                <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                <span class="hidden-xs">{!! Auth::user()->name !!}</span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- The user image in the menu -->
                                <li class="user-header">
                                    <img src="{{ url('/img/usuario-01.png') }}"class="img-circle" alt="User Image"/>
                                    <p>
                                        {!! Auth::user()->name !!}
                                        <small>Miembro desde {!! Auth::user()->created_at->format('M. Y') !!}</small>
                                    </p>
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="#" class="btn btn-default btn-flat">{{ trans('custom.profile') }}</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="{!! url('/logout') !!}" class="btn btn-default btn-flat"
                                            onclick="event.preventDefault(); document.getElementById('logout-form').submit();">
                                            {{ trans('custom.exit') }}
                                        </a>
                                        <form id="logout-form" action="{{ url('/logout') }}" method="POST" style="display: none;">
                                            {{ csrf_field() }}
                                        </form>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#" data-toggle="control-sidebar"><i class="fa fa-bell"></i></a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>

        <!-- Left side column. contains the logo and sidebar -->
        @include('layouts.sidebar')
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            @yield('content')
        </div>

        
        <!-- The Right Sidebar -->
        <aside class="control-sidebar control-sidebar-dark control-sidebar-close">
            <!-- Create the tabs -->
            <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
                <li class="active">
                    <a href="#control-sidebar-home-tab" data-toggle="tab">
                        <i class="fa fa-clock-o"></i></a>
                </li>
            </ul>
            <!-- Tab panes -->
            <div class="tab-content">
              <!-- Home tab content -->
              <div class="tab-pane active" id="control-sidebar-home-tab">
                <h3 class="control-sidebar-heading">{{ trans('custom.recent_activity') }}</h3>
                <ul class="control-sidebar-menu">
                    @foreach($audits as $item)
                    <li>
                        <a href="#">
                            @if($item->event == "created")
                                <i class="menu-icon fa fa-plus bg-green"></i>
                            @elseif($item->event == "updated")
                                <i class="menu-icon fa fa-refresh bg-yellow"></i>
                            @else 
                                <i class="menu-icon fa fa-trash-o bg-red"></i>
                            @endif    

                            <div class="menu-info">
                                <h4 class="control-sidebar-subheading">
                                @if($item->event == "created")
                                    Registro Creado en 
                                @elseif($item->event == "updated")
                                    Registro Modificado en 
                                @else 
                                    Registro Eliminado en 
                                @endif 
                                
                                @if($item->auditable_type == "App\Models\centers")
                                    Centros
                                @elseif($item->auditable_type == "App\Models\clinicalHistory")
                                    Historias Clinicas
                                @elseif($item->auditable_type == "App\Models\patient")
                                    Pacientes
                                @elseif($item->auditable_type == "App\Models\procedures")
                                    Procedimientos
                                @elseif($item->auditable_type == "App\Models\users")
                                    Usuarios
                                @elseif($item->auditable_type == "App\User")
                                    Usuarios
                                @else
                                    EPS
                                @endif 
                              </h4>

                              <p>{{ $item->created_at }}</p>
                            </div>
                        </a>
                    </li>
                    @endforeach
                    {{ $audits->links() }}
                </ul>
                <!-- /.control-sidebar-menu -->
                <!-- /.control-sidebar-menu -->

              </div>
              
              <!-- /.tab-pane -->
              <!-- Stats tab content -->
              <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
              <!-- /.tab-pane -->
              <!-- Settings tab content -->
              <div class="tab-pane" id="control-sidebar-settings-tab">
                <form method="post">
                  <h3 class="control-sidebar-heading">General Settings</h3>

                  <div class="form-group">
                    <label class="control-sidebar-subheading">
                      Report panel usage
                      <input type="checkbox" class="pull-right" checked="">
                    </label>

                    <p>
                      Some information about this general settings option
                    </p>
                  </div>
                  <!-- /.form-group -->

                  <div class="form-group">
                    <label class="control-sidebar-subheading">
                      Allow mail redirect
                      <input type="checkbox" class="pull-right" checked="">
                    </label>

                    <p>
                      Other sets of options are available
                    </p>
                  </div>
                  <!-- /.form-group -->

                  <div class="form-group">
                    <label class="control-sidebar-subheading">
                      Expose author name in posts
                      <input type="checkbox" class="pull-right" checked="">
                    </label>

                    <p>
                      Allow the user to show his name in blog posts
                    </p>
                  </div>
                  <!-- /.form-group -->

                  <h3 class="control-sidebar-heading">Chat Settings</h3>

                  <div class="form-group">
                    <label class="control-sidebar-subheading">
                      Show me as online
                      <input type="checkbox" class="pull-right" checked="">
                    </label>
                  </div>
                  <!-- /.form-group -->

                  <div class="form-group">
                    <label class="control-sidebar-subheading">
                      Turn off notifications
                      <input type="checkbox" class="pull-right">
                    </label>
                  </div>
                  <!-- /.form-group -->

                  <div class="form-group">
                    <label class="control-sidebar-subheading">
                      Delete chat history
                      <a href="javascript:void(0)" class="text-red pull-right"><i class="fa fa-trash-o"></i></a>
                    </label>
                  </div>
                  <!-- /.form-group -->
                </form>
              </div>
              <!-- /.tab-pane -->
            </div>
          </aside>        <!-- The sidebar's background -->
        <!-- This div must placed right after the sidebar for it to work-->
        <div class="control-sidebar-bg"></div>
    </div>
<!-- Main Footer -->
        <footer class="main-footer" style="max-height: 100px;text-align: center">
            <strong>Copyright © 2017 <a href="http://dibso.co/">Dibso</a>.</strong>{{ trans('custom.rights_reserved') }}
        </footer>
@else
    <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">

                <!-- Collapsed Hamburger -->
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#app-navbar-collapse">
                    <span class="sr-only">Toggle Navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <!-- Branding Image -->
                <a class="navbar-brand" href="{!! url('/') !!}">
                    <img src="{{ url('/img/LOGO_BLANCO.png') }}" width="150" height="46" alt="LOGO BLANCO"/>
                </a>
            </div>

            <div class="collapse navbar-collapse" id="app-navbar-collapse">
                <!-- Left Side Of Navbar -->
                <ul class="nav navbar-nav">
                    <li><a href="{!! url('/home') !!}">Home</a></li>
                </ul>

                <!-- Right Side Of Navbar -->
                <ul class="nav navbar-nav navbar-right">
                    <!-- Authentication Links -->
                    <li><a href="{!! url('/login') !!}">Login</a></li>
                    <li><a href="{!! url('/register') !!}">Register</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    @yield('content')
                </div>
            </div>
        </div>
    </div>


    @endif
    
    <!-- jQuery 3.1.1 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/icheck.min.js"></script>

    <!-- AdminLTE App -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.3.11/js/app.min.js"></script>

    <!-- jvectormap -->
    <script src="{{ url('/js/jquery-jvectormap-1.2.2.min.js') }}"></script>
    <script src="{{ url('/js/jquery-jvectormap-world-mill-en.js') }}"></script>
    
    <script src="{{ url('/js/jquery.dataTables.yadcf.js') }}"></script>
    <script
        src="https://code.jquery.com/ui/1.12.0-rc.1/jquery-ui.js"
        integrity="sha256-IY2gCpIs4xnQTJzCIPlL3uUgSOwVQYD9M8t208V+7KA="
        crossorigin="anonymous"></script>
        
    <script src="https://cdn.datatables.net/1.10.15/js/dataTables.material.min.js"></script>
    @yield('scripts')
    
    <script type="text/javascript">
        $("#sidebar_button_toggle").click(function(){
            $("#bigLogo").toggleClass("hidden");
            $("#smallLogo").toggleClass("hidden");
        });
    </script>
    @stack('scripts')
    
</body>
</html>