/*
 * Copyright 2016-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

;(function (config) {
    const HtmlWebpackPlugin = require('html-webpack-plugin');

    config.output.filename = "[name].bundle.js"

    config.plugins.push(
        new HtmlWebpackPlugin({
            title: 'Kotlin JS Example'
        })
    )
})(config);
