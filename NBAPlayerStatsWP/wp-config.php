<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the
 * installation. You don't have to use the web site, you can
 * copy this file to "wp-config.php" and fill in the values.
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
define( 'DB_NAME', 'nba_player_stats' );

/** MySQL database username */
define( 'DB_USER', 'root' );

/** MySQL database password */
define( 'DB_PASSWORD', '' );

/** MySQL hostname */
define( 'DB_HOST', 'localhost' );

/** Database Charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The Database Collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         '$mFp~0C2//pqX))l],~loTCL%u3r}A8`n0I,-UZ:XLij,;4 f(w@$w~zCrE#^v &' );
define( 'SECURE_AUTH_KEY',  'P`YcE7hr<>x|;?B[E0(Feo0Y>m!Cs.h|oen;{0rBxmT89cnBDkI(e>|yn=/@#,~d' );
define( 'LOGGED_IN_KEY',    'KR%~2T,T]8YdeU0jDIFHbG?H]9JGjK V4WyTA}bgK-J+5{vy>$PlRE~O{jM~#?_b' );
define( 'NONCE_KEY',        'XbS=r7WoJ .g^:1$Gk5G{k[Lok!(qm!]6Um= Ux>^bSwCJ5klTgZh(lJ3&B:aVSv' );
define( 'AUTH_SALT',        'IY.xp%cV.>ESl]I.1aG#4T+vPk>[>05IhCQNh`;u-a8d~.#)hu(bfa(,B /TkydT' );
define( 'SECURE_AUTH_SALT', ' vsZ5E(UA da_KUL2Sep6eN|b70EDx}*cFL_7pV$h.6:^hLLXf]8s(43]uZv3#|/' );
define( 'LOGGED_IN_SALT',   'OZ3o1#k|M:CGNAazC=!6?I*S>R+ bmBj7,~rQhmkZdl~VFY=-DUd^u}3hss iU8V' );
define( 'NONCE_SALT',       '+7d?*@30sA7*A.:(z3y#S]FMVz^1X734!ic4~wYn(] MdM&z?jJb^K%rbTboZ9Cf' );

/**#@-*/

/**
 * WordPress Database Table prefix.
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

/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';
