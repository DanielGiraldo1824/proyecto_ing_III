<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\users;
use App\Models\centers;
use Charts;
use Illuminate\Support\Facades\DB;
use App\Mail\OrderShipped;
use App\Mail\Home as HomeMail;
use Illuminate\Support\Facades\Mail;

class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $userModel = users::all()->count();
        
        /*
        //return $testDataTable->render('tests.index');
        $chart = Charts::multi('bar', 'material')
            // Setup the chart settings
            ->title("Pacientes")
            // A dimension of 0 means it will take 100% of the space
            ->dimensions(0, 0) // Width x Height
            // This defines a preset of colors already done:)
            ->template("material")
            // You could always set them manually
            // ->colors(['#2196F3', '#F44336', '#FFC107'])
            // Setup the diferent datasets (this is a multi chart)
            ->dataset('Element 1', [5,20,100])
            ->dataset('Element 2', [15,30,80])
            ->dataset('Element 3', [25,10,40])
            // Setup what the values mean
            ->labels(['One', 'Two', 'Three']);
    */
         $chart = Charts::database(users::all(), 'line', 'highcharts')
                 ->title("Pacientes / Dia")
                 // A dimension of 0 means it will take 100% of the space
                ->dimensions(0, 300) // Width x Height
                 ->elementLabel("Total")
                 ->dateColumn('created_at')
                 ->groupByDay();
         
         $chart_2 = Charts::database(centers::all(), 'bar', 'highcharts')
                 ->title("Procedimientos / Dia")
                 // A dimension of 0 means it will take 100% of the space
                ->dimensions(0, 300) // Width x Height
                 ->elementLabel("Total")
                 ->dateColumn('created_at')
                 ->groupByDay();
         
        
        
        return view('home', [   'users_count' => $userModel,
                                'patients_count' => '0',
                                'procedures_count' => '0',
                                'Histories_count' => '0',
                                'chart' => $chart,
                                'chart_2' => $chart_2]);
        
    }
    
    public function iniHome(){
        
        
    }

    /**
     * Send email Quick Mail
     */
    public function sendEmail(Request $request)
    {
        Mail::to('test.email@gmail.com', 'test')
            ->send(new HomeMail($request));
        
        flash()->success('Correo enviado', '');
        return back();
    }
    
}
