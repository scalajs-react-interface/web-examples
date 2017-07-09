'use strict';

const webpack = require('webpack');
const path = require('path');

const IS_PRODUCTION = process.env.NODE_ENV === 'production';

const config = {

    entry: {
        mainpage: './index.web.js'
    },
    output: {
        path: __dirname + '/build',
        publicPath: "/build/",
        filename: '[name]-bundle.js'
    },
    plugins: [
        new webpack.DefinePlugin({
            'SERVICE_URL': JSON.stringify(process.env.SRI_APP_RELAY_URL)
        })
    ],
    devServer: {
        historyApiFallback: true,
        compress: true,
        port: 8090
    },
    module: {
        loaders: [

            {
                test: /\.json$/,
                loader: "json-loader"
            },

            {
                test: /\.css$/,
                loader: 'style-loader!css-loader'
            },
            {
                test: /\.woff(\?v=\d+\.\d+\.\d+)?$/,
                loader: "url-loader?limit=10000&mimetype=application/font-woff"
            }, {
                test: /\.woff2(\?v=\d+\.\d+\.\d+)?$/,
                loader: "url-loader?limit=10000&mimetype=application/font-woff"
            }, {
                test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,
                loader: "url-loader?limit=10000&mimetype=application/octet-stream"
            }, {
                test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,
                loader: "file-loader"
            },
            {
                test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,
                loader: "url-loader?limit=10000&mimetype=image/svg+xml"
            },
            {
                test: /\.(png|jpg|svg)$/,
                loaders: [
                    'url-loader?limit=8192',
                    {
                        loader: 'image-webpack-loader',
                        query: {
                            optipng: {
                                optimizationLevel: 4,
                            },
                            mozjpeg: {
                                progressive: true,
                            }
                        }
                    }
                ]
            } // inline base64 URLs for <=8k images, direct URLs for the rest,

        ]
    }


};

if (IS_PRODUCTION) {
    // set production config
    console.log("Production Mode")
} else {
    console.log("Development mode");
    config.plugins.push(
        new webpack.DefinePlugin({
            'process.env': {
                'NODE_ENV': JSON.stringify('development')
            }
        })
    )
}


module.exports = config;