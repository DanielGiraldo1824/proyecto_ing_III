<?php

namespace App\Providers;

use Illuminate\Support\ServiceProvider;
use Illuminate\Support\Facades\DB;
use View;
use Illuminate\Pagination\LengthAwarePaginator;
use Illuminate\Support\Collection;
use App\audits;

class ComposerServiceProvider extends ServiceProvider
{
    /**
     * Bootstrap the application services.
     *
     * @return void
     */
    public function boot()
    {
        
        View::composer('layouts.app', function($view){
            $audits = DB::table('audits')->orderBy('created_at', 'desc')->paginate(7);
            
            /*echo '<pre>';
            print_r($audits);
            echo '</pre>';
            die();*/
            $view->with('audits', $audits);
        });
    }

    /**
     * Register the application services.
     *
     * @return void
     */
    public function register()
    {
        //
    }
}
