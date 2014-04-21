'use strict';
var util = require('util');
var path = require('path');
var yeoman = require('yeoman-generator');
var _s = require('underscore.string');


var MavenGenerator = module.exports = function MavenGenerator(args, options, config) {
    yeoman.generators.Base.apply(this, arguments);

    this.on('end', function () {
        this.installDependencies({ skipInstall: options['skip-install'] });
    });

    this.pkg = JSON.parse(this.readFileAsString(path.join(__dirname, '../package.json')));
};

util.inherits(MavenGenerator, yeoman.generators.Base);

MavenGenerator.prototype.askFor = function askFor() {
    var cb = this.async();

    // have Yeoman greet the user.
    console.log(this.yeoman);

    var prompts = [
        {
            type: 'input',
            name: 'baseName',
            message: '(1/2) What is the base name of your application?',
            default: 'example'
        },
        {
            type: 'input',
            name: 'packageName',
            message: '(2/2) What is your default package name?',
            default: 'com.mycompany.myapp'
        }
    ];

    this.prompt(prompts, function (props) {
        this.baseName = props.baseName;
        this.packageName = props.packageName;
        this.version = '1.0-SNAPSHOT';
        cb();
    }.bind(this));
};

MavenGenerator.prototype.app = function app() {
    //this.mkdir('app');
    //this.mkdir('app/templates');

    var packageFolder = this.packageName.replace(/\./g, '/');
    this.mkdir("src/main/java/" + packageFolder);
    this.mkdir("src/main/webapp/");
    this.mkdir("src/main/web/");
    this.mkdir("src/test/java/");
    this.mkdir("src/test/web/");

    // Java sources
    var srcFolder = 'src/main/java/' + packageFolder + '/';
    this.mkdir(srcFolder);
    this.template('src/main/java/Application.java', srcFolder + '/Application.java');
    this.template('src/main/java/rest/HelloRestService.java', srcFolder + 'rest/HelloRestService.java');

    this.template('src/main/java/dao/api/GenericDao.java', srcFolder + 'dao/api/GenericDao.java');
    this.template('src/main/java/dao/api/UserDao.java', srcFolder + 'dao/api/UserDao.java');
    this.template('src/main/java/dao/impl/GenericJPADao.java', srcFolder + 'dao/impl/GenericJPADao.java');
    this.template('src/main/java/dao/impl/UserJPADao.java', srcFolder + 'dao/impl/UserJPADao.java');
    this.template('src/main/java/entity/User.java', srcFolder + 'entity/User.java');
    this.template('src/main/java/rest/UserRestService.java', srcFolder + 'rest/UserRestService.java');

    this.template('src/main/resources/META-INF/persistence.xml', 'src/main/resources/META-INF/persistence.xml');

    // Webapp folder
    this.copy('src/main/webapp/WEB-INF/web.xml', 'src/main/webapp/WEB-INF/web.xml');
    this.copy('src/main/webapp/WEB-INF/openejb-jar.xml', 'src/main/webapp/WEB-INF/openejb-jar.xml');
    this.copy('src/main/webapp/WEB-INF/beans.xml', 'src/main/webapp/WEB-INF/beans.xml');

    // Web folder
    this.template('src/main/web/_index.html', 'src/main/web/index.html');
    this.copy('src/main/web/views/main.html', 'src/main/web/views/main.html');
    this.template('src/main/web/scripts/_app.js', 'src/main/web/scripts/app.js');
    this.template('src/main/web/scripts/controllers/_main.js', 'src/main/web/scripts/controllers/main.js');
    this.copy('src/main/web/styles/main.css', 'src/main/web/styles/main.css');
    this.copy('src/main/web/favicon.ico', 'src/main/web/favicon.ico');
    this.copy('src/main/web/robots.txt', 'src/main/web/robots.txt');

    // Web test folder
    this.template('src/test/web/spec/controllers/_main.js', 'src/test/web/spec/controllers/main.js');

    // Root folder
    this.copy('bowerrc', '.bowerrc');
    this.copy('jshintrc', '.jshintrc');
    this.template('gitignore', '.gitignore');
    this.copy('Gruntfile.js', 'Gruntfile.js');
    this.copy('karma.conf.js', 'karma.conf.js');
    this.template('_pom.xml', 'pom.xml');
    this.template('_package.json', 'package.json');
    this.template('_bower.json', 'bower.json');
};

MavenGenerator.prototype.projectfiles = function projectfiles() {
    this.copy('editorconfig', '.editorconfig');
    this.copy('jshintrc', '.jshintrc');
};
