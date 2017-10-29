<?php


/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| This file is where you may define all of the routes that are handled
| by your application. Just tell Laravel the URIs it should respond
| to using a Closure or controller method. Build something great!
|
*/

Route::get('/', function () {
    return redirect('home');
});


Auth::routes();

Route::resource('/home', 'HomeController@index',['only' => 'index']);

Route::post('home','HomeController@sendEmail');

Route::resource('users', 'usersController');

Route::get('email-view', function (){
    return view('auth.emails.home');
    
});
Route::get('audits-undo/{id}/{module}/{audit}', 'AuditController@undoDelete');

Route::resource('centers', 'centersController');

Route::resource('roles', 'RolesController');

Route::resource('audits', 'AuditController',['except'=> ['edit', 'create']]);

