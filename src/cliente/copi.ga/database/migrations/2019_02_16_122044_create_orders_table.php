<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateOrdersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('orders', function (Blueprint $table) {
            $table->increments('id');
            $table->timestamps();
            $table->string('filename');
            $table->string('filehash');
            $table->string('filesize');
            $table->boolean('bothSides');
            $table->boolean('color');
            $table->unsignedInteger('pagesForSide');
            $table->float('price')->nullable();
            $table->boolean('confirmed')->default(false);

            $table->boolean('accepted')->default(false); //accettato dalla copisteria
            $table->boolean('printed')->default(false); //stampato dalla copisteria

            $table->unsignedInteger('paper_id');
            $table->unsignedInteger('bookbinding_id');
            $table->unsignedInteger('credit_id');
            $table->foreign('paper_id')->references('id')->on('papers');
            $table->foreign('bookbinding_id')->references('id')->on('bookbindings');
            $table->foreign('credit_id')->references('id')->on('credits');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('orders');
    }
}
