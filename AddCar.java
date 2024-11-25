public function store_car(Request $request): RedirectResponse
{
    if (strlen($request->name) == 0) {
        return redirect('/settings/car/add')->with('error', 'Isikan nama mobil!')->withInput();
    } else if (strlen($request->name) > 50) {
        return redirect('/settings/car/add')->with('error', 'Isikan nama mobil maksimal 50 karakter!')->withInput();
    } else if (strlen($request->all_in) == 0) {
        return redirect('/settings/car/add')->with('error', 'Isikan harga sewa all in!')->withInput();
    } else if (strlen($request->all_in) < 6) {
        return redirect('/settings/car/add')->with('error', 'Isikan harga sewa all in dengan benar! (Minimal input Rp100.000)')->withInput();
    } else if (strlen($request->all_in) > 7) {
        return redirect('/settings/car/add')->with('error', 'Isikan harga sewa all in dengan benar! (Maksimal input Rp9.999.999)')->withInput();
    } else if (strlen($request->car_only) == 0) {
        return redirect('/settings/car/add')->with('error', 'Isikan harga sewa car only!')->withInput();
    } else if (strlen($request->car_only) < 6) {
        return redirect('/settings/car/add')->with('error', 'Isikan harga sewa car only dengan benar! (Minimal input Rp100.000)')->withInput();
    } else if (strlen($request->car_only) > 7) {
        return redirect('/settings/car/add')->with('error', 'Isikan harga sewa car only dengan benar! (Maksimal input Rp9.999.999)')->withInput();
    } else if (strlen($request->person) == 0) {
        return redirect('/settings/car/add')->with('error', 'Isikan kapasitas mobil!')->withInput();
    } else if (strlen($request->person) > 7) {
        return redirect('/settings/car/add')->with('error', 'Isikan kapasitas mobil maksimal 7 karakter menggunakan angka! (contoh 5 - 7)')->withInput();
    } else if (strlen($request->year) == 0) {
        return redirect('/settings/car/add')->with('error', 'Isikan tahun mobil!')->withInput();
    } else if (strlen($request->year) != 4) {
        return redirect('/settings/car/add')->with('error', 'Isikan tahun mobil dengan benar!')->withInput();
    } else {
      $request->validate([
          'image' => 'required|image|mimes:jpeg,jpg,png|max:2048',
      ]);
  
      $image = $request->file('image');
      $image->storeAs('public/rental_images', $image->hashName());
  
      Car::create([
          'image' => $image->hashName(),
          'name' => $request->name,
          'all_in' => $request->all_in,
          'car_only' => $request->car_only,
          'person' => $request->person,
          'transmission' => $request->transmission,
          'year' => $request->year,
          'type' => $request->type
      ]);
  
      return redirect()->route('car')->with(['success' => 'Data saved successfully!']);
    }
}
