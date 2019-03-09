<?php

namespace Tests\Unit;

use Tests\TestCase;
use App\User;
use App\Order;
use App\Http\Controllers\OrderController;
use Illuminate\Http\Request;
use Illuminate\Foundation\Testing\WithFaker;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithoutMiddleware;
use Illuminate\Support\Facades\Storage;
use Illuminate\Http\UploadedFile;

class OrderTest extends TestCase
{

    public function testNewCorrectOrder()
    {

      $this->assertDatabaseHas('users', [
        'email' => 'akille@a.a'
      ]);
      $user = User::find(2);
      $this->be($user);

      $stub = __DIR__.'/../U14.pdf';
      $name = str_random(8).'.pdf';
      $path = sys_get_temp_dir().'/'.$name;

      copy($stub, $path);

      $file = new UploadedFile($path, $name, 'document/pdf', filesize($path), null, true);



      $request = Request::create('/order', 'POST', [
        'paper' => '1',
        'bookbinding' => '1',
        'bothSides' => 'yes',
        'color' => 'no',
        'pagesForSide' => '1',
        'fileUpload' => $file,
        'selezione_copisteria' => '1'
      ]);

      $controller = new OrderController();

      $response = $controller->createOrder($request);

      $this->assertDatabaseHas('orders', [
        'filename' => $file->hashName()
      ]);

    }




}
