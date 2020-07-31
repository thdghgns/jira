module.exports = {
  outputDir: '../src/main/resources/static',
  indexPath: '../static/index.html',
  devServer: {
    disableHostCheck: true,
    proxy: 'http://localhost:8080'
  },

  transpileDependencies: ['vuetify'],

  pluginOptions: {
    i18n: {
      locale: 'en',
      fallbackLocale: 'en',
      localeDir: 'locales',
      enableInSFC: false,
    },
  },
}
