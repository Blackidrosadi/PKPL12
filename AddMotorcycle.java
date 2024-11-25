public function store_motorcycle(Request $request): RedirectResponse
{
    if (strlen($request->name) == 0) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan nama motor!')->withInput();
    } else if (strlen($request->name) > 50) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan nama motor maksimal 50 karakter!')->withInput();
    } else if (strlen($request->perday) == 0) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan harga sewa daily!')->withInput();
    } else if (strlen($request->perday) < 5) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan harga sewa daily dengan benar! (Minimal input Rp10.000)')->withInput();
    } else if (strlen($request->perday) > 7) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan harga sewa daily dengan benar! (Maksimal input Rp9.999.999)')->withInput();
    } else if (strlen($request->perweek) == 0) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan harga sewa weekly!')->withInput();
    } else if (strlen($request->perweek) < 6) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan harga sewa weekly dengan benar! (Minimal input Rp100.000)')->withInput();
    } else if (strlen($request->perweek) > 7) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan harga sewa weekly dengan benar! (Maksimal input Rp9.999.999)')->withInput();
    } else if (strlen($request->permonth) == 0) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan harga sewa monthly!')->withInput();
    } else if (strlen($request->permonth) < 6) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan harga sewa monthly dengan benar! (Minimal input Rp100.000)')->withInput();
    } else if (strlen($request->permonth) > 7) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan harga sewa monthly dengan benar! (Maksimal input Rp9.999.999)')->withInput();
    } else if (strlen($request->cc) == 0) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan kapasitas cc motor!')->withInput();
    } else if (strlen($request->cc) < 3) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan kapasitas cc motor minimal 100cc!')->withInput();
    } else if (strlen($request->cc) > 4) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan kapasitas cc motor maksimal 4 karakter menggunakan angka!')->withInput();
    } else if (strlen($request->year) == 0) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan tahun motor!')->withInput();
    } else if (strlen($request->year) != 4) {
        return redirect('/settings/motorcycle/add')->with('error', 'Isikan tahun motor dengan benar!')->withInput();
    } else {
      $request->validate([
          'image' => 'required|image|mimes:jpeg,jpg,png|max:2048',
      ]);
  
      $image = $request->file('image');
      $image->storeAs('public/rental_images', $image->hashName());
  
      Motorcycle::create([
          'image' => $image->hashName(),
          'name' => $request->name,
          'perday' => $request->perday,
          'perweek' => $request->perweek,
          'permonth' => $request->permonth,
          'cc' => $request->cc,
          'year' => $request->year,
          'transmission' => $request->transmission
      ]);
  
      return redirect()->route('motorcycle')->with(['success' => 'Data saved successfully!']);
    }
}
