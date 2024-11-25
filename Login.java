public function signin(Request $request)
{
    $rules = [
        'email' => ['required', 'regex:/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/'],
    ];

    $validator = Validator::make($request->all(), $rules);
    
    if (strlen($request->email) == 0) {
        return redirect('/settings')->with('error', 'Harap mengisi email!')->withInput();
    } else if ($validator->fails()) {
        return redirect('/settings')->with('error', 'Email tidak valid, masukkan email sesuai format!')->withInput();
    } else if (strlen($request->email) > 50) {
        return redirect('/settings')->with('error', 'Email teralu panjang, masukkan dengan maksimal 50 karakter!')->withInput();
    } else if (strlen($request->password) == 0) {
        return redirect('/settings')->with('error', 'Harap mengisi password!')->withInput();
    } else if (strlen($request->password) > 30) {
        return redirect('/settings')->with('error', 'Password teralu panjang, masukkan dengan maksimal 30 karakter!')->withInput();
    } else {
      $credentials = $request->only('email', 'password');

      if (Auth::attempt($credentials)) {
          return redirect()->intended('/settings/general');
      } else {
          return redirect('/settings')->with('error', 'Kredensial tidak valid. Silakan coba lagi.')->withInput();
      }
    }
}
