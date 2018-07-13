var gulp = require('gulp'),
    //browserSync = require('browser-sync').create(),
    browserify = require('browserify'),
    babelify = require('babelify'),
    source = require("vinyl-source-stream");
    // sass = require('gulp-sass'),
    // useref = require('gulp-useref'),
    // uglify = require('gulp-uglify'),
    // gulpIf = require('gulp-if'),
    // cssnano = require('gulp-cssnano'),
    // imagemin = require('gulp-imagemin'),
    // cache = require('gulp-cache'),
    // del = require('del'),
    // runSequence = require('run-sequence'),
    // babel = require('gulp-babel'),
    // sourcemaps = require('gulp-sourcemaps');

//compile sass files
// gulp.task('sass', function(){
// 	return gulp.src('app/src/scss/**/*.scss')
// 		.pipe(sass())
// 		.pipe(gulp.dest('app/css'))
// 		.pipe(browserSync.reload({ stream: true }));
// });

//compile ES6 and JSX
// gulp.task("babel", function () {
//   return gulp.src("app/src/js/**/*.js")
//   	.pipe(sourcemaps.init())
//     .pipe(babel({ presets: ['es2015','react','env'] }))
//     .pipe(sourcemaps.write("."))
//     .pipe(gulp.dest("app/js/"))
//     .pipe(browserSync.reload({ stream: true }));
// });
//browserify

gulp.task("browserify", function () {
  return browserify({
        entries: ["src/main/webapp/WEB-INF/js/main.js"]
    })
    .transform(babelify.configure({
        presets : ["es2015","react"]
    }))
    .bundle()
    .pipe(source("app.js"))
    .pipe(gulp.dest("src/main/webapp/resources/js/"));
});

//Autosync changes in browser
// gulp.task('browserSync', function(){
// 	browserSync.init({
// 		server: {
// 			baseDir: 'app'
// 		}
// 	});
// });

// //JS HTML Files To PROD, Minify
// gulp.task('useref', function(){
// 	return gulp.src('app/*.html')
// 		.pipe(useref())
// 		.pipe(gulpIf("app/js/**/*.js", uglify()))
// 		.pipe(gulpIf("*.css", cssnano()))
// 		.pipe(gulp.dest('dist'));
// });
//
// //Optimizing Images
// gulp.task('images', function(){
// 	return gulp.src('app/images/**/*.+(png|jpg|gif|svg)')
// 		.pipe(cache(imagemin({interlaced: true})))
// 		.pipe(gulp.dest('dist/images'));
// });
//
// //Fonts
// gulp.task('fonts', function(){
// 	return gulp.src('app/fonts/**/*')
// 		.pipe(gulp.dest('dist/fonts'));
// });
//
// //Cleaner
// gulp.task('clean:dist', function(){
// 	return del.sync('dist');
// });

//listening
gulp.task('watch', [], function(callback){
	//gulp.watch('app/src/scss/**/*.scss', ['sass']);
	//React & JSX
	gulp.watch('src/main/webapp/WEB-INF/js/**/*.{js,jsx}', ['browserify']);

	//reloads the browser whenever HTML or JS files change
	//gulp.watch('app/*.html', browserSync.reload);
	//gulp.watch('app/js/**/*.js', browserSync.reload);
});

// gulp.task('default',  function(callback){
// 	runSequence(['sass','browserSync','watch'], callback);
// });

//Build
// gulp.task('build', function(callback){
// 	runSequence('clean:dist', ['sass','useref','images','fonts'], callback);
// 	console.log('Building files');
// });