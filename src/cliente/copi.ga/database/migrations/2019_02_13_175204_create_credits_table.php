<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateCreditsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('credits', function (Blueprint $table) {
            $table->increments('id');
            $table->timestamps();
            $table->float('disponibile');
            $table->float('totale');
            $table->unsignedInteger('printshop_id');
            $table->unsignedInteger('user_id');

            $table->unique(array('printshop_id', 'user_id'));

            $table->foreign('printshop_id')->references('id')->on('printshops');
            $table->foreign('user_id')->references('id')->on('users');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('credits');
    }
}
