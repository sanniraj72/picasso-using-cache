# picasso-using-cache

This app will help you to understand the picasso using cache


File file = new File(getCacheDir(), "picasso-cache");
Cache cache = new Cache(file, 15 * 1024 * 1024);

OkHttpClient.Builder builder = new OkHttpClient.Builder().cache(cache);
  
Picasso picasso = new Picasso.Builder(this).listener(new Picasso.Listener() {
  @Override
  public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
  }
}).downloader(new OkHttp3Downloader(builder.build())).build();
