<?php

namespace App\Mail;

use Illuminate\Bus\Queueable;
use Illuminate\Mail\Mailable;
use Illuminate\Queue\SerializesModels;
use Illuminate\Http\Request;
use Illuminate\Contracts\Queue\ShouldQueue;

class Home extends Mailable
{
    use Queueable, SerializesModels;

    public $request;
    
    /**
     * Create a new message instance.
     *
     * @return void
     */
    public function __construct(Request $request)
    {
        $this->request = $request;
        
    }

    /**
     * Build the message.
     *
     * @return $this
     */
    public function build()
    {
        return $this->view('auth.emails.home')
                ->with('name', 'Administrador')
                ->with('messager', $this->request->input('content'))
                //->from('dibso.magicid@gmail.com', 'MagicID')//Ya esta en el config/mail
                ->subject($this->request->input('subject'));
    }
}
