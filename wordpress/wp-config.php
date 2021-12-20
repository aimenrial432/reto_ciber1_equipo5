<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the installation.
 * You don't have to use the web site, you can copy this file to "wp-config.php"
 * and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * MySQL settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://wordpress.org/support/article/editing-wp-config-php/
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'GIIF' );

/** MySQL database username */
define( 'DB_USER', 'user_de_wordpress' );

/** MySQL database password */
define( 'DB_PASSWORD', 'contrasena_extremadamente_segura_1245677' );

/** MySQL hostname */
define( 'DB_HOST', '10.122.27.95' );

/** Database charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The database collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication unique keys and salts.
 *
 * Change these to different unique phrases! You can generate these using
 * the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}.
 *
 * You can change these at any point in time to invalidate all existing cookies.
 * This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         '@UAR9rh)#q256g[GgD6 %fE`@@VgQ5rkv^g=G4a(pY.8pg&U96vtFj?93I^[!]7^' );
define( 'SECURE_AUTH_KEY',  '}gIV>gpNn`m+Bjd6 QPr`)h2c1{RGw2&RhQWN9n V*1nYLG!!~z0icPWvz=9Eq0B' );
define( 'LOGGED_IN_KEY',    '4#E~YZA)iL2EOuPTK(sQW_/h23/&}(7tB*,C@m|T;/7$F#cc9*DK:E5Xn$c/Rg$p' );
define( 'NONCE_KEY',        ']4{f,Y{29vBOtTxjxG1FB;opdCnnRW[Pl=o-p?fh`[A.,,et1Ff@$l4gQQLhEF@S' );
define( 'AUTH_SALT',        'J3gu*Y< ^G#{~tmMKMMnl_b<y.NQi/vzgeUomy$PJk?+g1#P~ShH}SRy:Goq0E+}' );
define( 'SECURE_AUTH_SALT', '-h~DHDh6UD@Lp*<|?MyD.r!d0L_srU>MG?R.U~bi92-O2.1/Bu7PbA#fu??+]k(V' );
define( 'LOGGED_IN_SALT',   '/&vV}PyyBu}xRGmEO<69sZu#-Kxg1}2>k<8nHBtYiD.w?e9X3=3Wkr[b0@%$A6zx' );
define( 'NONCE_SALT',       'E^{#aZWVm70_*z%h,&<ggdot<W<OX%m)Ko,LoL5w9ML@g)#8h)o!9J>qUVY3LH}d' );

/**#@-*/

/**
 * WordPress database table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://wordpress.org/support/article/debugging-in-wordpress/
 */
define( 'WP_DEBUG', false );

/* Add any custom values between this line and the "stop editing" line. */



/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';

//header('X-Frame_Options: SAMEORIGIN');
