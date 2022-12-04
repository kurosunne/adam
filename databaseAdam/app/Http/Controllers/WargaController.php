<?php

namespace App\Http\Controllers;

use App\Models\Warga;
use Illuminate\Http\Request;

class WargaController extends Controller
{
    function getWarga(Request $request){
        return response()->json(Warga::where("wa_username",$request->username)->get(), 200);
    }
}
