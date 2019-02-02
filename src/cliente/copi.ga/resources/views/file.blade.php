@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Quali tipi di file vuoi ricevere dai tuoi clienti?</div>

                <div class="card-body">



                  <div class="container">
                    <div class="table-wrapper">
                      <div class="table-title">
                        <div class="row">
                          <div class="col-sm-8"><h2>Employee <b>Details</b></h2></div>
                          <div class="col-sm-4">
                            <button type="button" class="btn btn-info add-new"><i class="fa fa-plus"></i> Add New</button>
                          </div>
                        </div>
                      </div>
                      <table class="table table-bordered">
                        <thead>
                          <tr>
                            <th>Name</th>
                            <th>Department</th>
                            <th>Phone</th>
                            <th>Actions</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td>John Doe</td>
                            <td>Administration</td>
                            <td>(171) 555-2222</td>
                            <td>
                              <a class="add" title="Add" data-toggle="tooltip"><i class="material-icons">&#xE03B;</i></a>
                              <a class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                              <a class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                            </td>
                          </tr>
                          <tr>
                            <td>Peter Parker</td>
                            <td>Customer Service</td>
                            <td>(313) 555-5735</td>
                            <td>
                              <a class="add" title="Add" data-toggle="tooltip"><i class="material-icons">&#xE03B;</i></a>
                              <a class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                              <a class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>




                </div>
            </div>
        </div>
    </div>
</div>
@endsection
