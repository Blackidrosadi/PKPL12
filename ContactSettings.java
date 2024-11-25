public function update_contacts(Request $request): RedirectResponse
{
    if (strlen($request->whatsapp) == 0) {
        return redirect('/settings/contacts')->with('error', 'Isikan nomor whatsapp pertama!');
    } else if (strlen($request->whatsapp) < 9) {
        return redirect('/settings/contacts')->with('error', 'Isikan nomor whatsapp pertama minimal 9 angka!');
    } else if (strlen($request->whatsapp) > 15) {
        return redirect('/settings/contacts')->with('error', 'Isikan nomor whatsapp pertama maksimal 15 angka!');
    } else if (strlen($request->whatsapp2) == 0) {
        return redirect('/settings/contacts')->with('error', 'Isikan nomor whatsapp kedua!');
    } else if (strlen($request->whatsapp2) < 9) {
        return redirect('/settings/contacts')->with('error', 'Isikan nomor whatsapp kedua minimal 9 angka!');
    } else if (strlen($request->whatsapp2) > 15) {
        return redirect('/settings/contacts')->with('error', 'Isikan nomor whatsapp kedua maksimal 15 angka!');
    } else if (strlen($request->whatsapp3) == 0) {
        return redirect('/settings/contacts')->with('error', 'Isikan nomor whatsapp ketiga!');
    } else if (strlen($request->whatsapp3) < 9) {
        return redirect('/settings/contacts')->with('error', 'Isikan nomor whatsapp ketiga minimal 9 angka!');
    } else if (strlen($request->whatsapp3) > 15) {
        return redirect('/settings/contacts')->with('error', 'Isikan nomor whatsapp ketiga maksimal 15 angka!');
    } else if (strlen($request->gmail) == 0) {
        return redirect('/settings/contacts')->with('error', 'Isikan gmail!');
    } else if (strlen($request->gmail) > 30) {
        return redirect('/settings/contacts')->with('error', 'Isikan gmail maksimal 30 karakter!');
    } else if (strlen($request->location) == 0) {
        return redirect('/settings/contacts')->with('error', 'Isikan link lokasi google maps!');
    } else if (strlen($request->location) > 80) {
        return redirect('/settings/contacts')->with('error', 'Isikan link lokasi google maps maksimal 80 karakter!');
    } else if (strlen($request->instagram) == 0) {
        return redirect('/settings/contacts')->with('error', 'Isikan username instagram!');
    } else if (strlen($request->instagram) > 30) {
        return redirect('/settings/contacts')->with('error', 'Isikan username instagram maksimal 30 karakter!');
    } else if (strlen($request->tiktok) == 0) {
        return redirect('/settings/contacts')->with('error', 'Isikan username tiktok!');
    } else if (strlen($request->tiktok) > 30) {
        return redirect('/settings/contacts')->with('error', 'Isikan username tiktok maksimal 30 karakter!');
    } else {
      $contact = Contact::find(1);
      $contact->update([
          'phone' => $request->whatsapp,
          'phone2' => $request->whatsapp2,
          'phone3' => $request->whatsapp3,
          'gmail' => $request->gmail,
          'location' => $request->location,
          'instagram' => $request->instagram,
          'tiktok' => $request->tiktok,
      ]);
  
      return redirect()->route('general-settings')->with(['success' => 'Contacts updated successfully!']);
    }
}
