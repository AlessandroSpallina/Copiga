<?php

namespace App\Mail;

use Illuminate\Bus\Queueable;
use Illuminate\Mail\Mailable;
use Illuminate\Queue\SerializesModels;
use Illuminate\Contracts\Queue\ShouldQueue;

class OrderMail extends Mailable
{
    use Queueable, SerializesModels;


    public $user, $title, $body, $order;

    /**
     * Create a new message instance.
     *
     * @return void
     */
    public function __construct($user, $title, $body, $order)
    {
        $this->user = $user;
        $this->title = $title;
        $this->body = $body;
        $this->order = $order;
    }

    /**
     * Build the message.
     *
     * @return $this
     */
    public function build()
    {
        return $this->view('emails.notify_order')
                    ->subject($this->title);
    }
}
