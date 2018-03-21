package tools;

public class ClassFinder {

    // public List<Class<?>> findClassesImpmenenting(final Class<?> interfaceClass, final Package fromPackage) {
    //
    // if (interfaceClass == null) {
    // System.out.println("bamiiii");
    // return null;
    // }
    //
    // if (fromPackage == null) {
    // System.out.println("asoummm");
    // return null;
    // }
    //
    // final List<Class<?>> rVal = new ArrayList<Class<?>>();
    // try {
    // final Class<?>[] targets = getAllClassesFromPackage(fromPackage.getName());
    // if (targets != null) {
    // System.out.println(targets.length);
    // for (Class<?> aTarget : targets) {
    // if (aTarget == null) {
    // System.out.println("nullllllllllllllllllllllllllllll");
    // continue;
    // } else if (aTarget.equals(interfaceClass)) {
    // System.out.println("moboyoooooo");
    // continue;
    // } else if (!aTarget.isAssignableFrom(interfaceClass)) {
    // System.out.println("weeee not nulll");
    // continue;
    // } else {
    // System.out.println("weeeeeeeeeeeeeeeeeeeeeeeeeeeee found");
    // rVal.add(aTarget);
    // }
    // }
    // }
    // } catch (ClassNotFoundException e) {
    // Debug.println("Error reading package name.");
    // } catch (IOException e) {
    // Debug.println("Error reading classes in package.");
    // }
    //
    // return rVal;
    // }
    //
    // /**
    // * Load all classes from a package.
    // *
    // * @param packageName
    // * @return
    // * @throws ClassNotFoundException
    // * @throws IOException
    // */
    // public Class<?>[] getAllClassesFromPackage(final String packageName) throws ClassNotFoundException, IOException {
    // ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    // assert classLoader != null;
    // String path = packageName.replace('.', '/');
    // System.out.println(path);
    // Enumeration<URL> resources = classLoader.getResources(path);
    // List<File> dirs = new ArrayList<File>();
    // while (resources.hasMoreElements()) {
    // URL resource = resources.nextElement();
    // dirs.add(new File(resource.getFile()));
    // }
    // ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
    // for (File directory : dirs) {
    // classes.addAll(findClasses(directory, packageName));
    // }
    // return classes.toArray(new Class[classes.size()]);
    // }
    //
    // /**
    // * Find file in package.
    // *
    // * @param directory
    // * @param packageName
    // * @return
    // * @throws ClassNotFoundException
    // */
    // public List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
    // List<Class<?>> classes = new ArrayList<Class<?>>();
    // if (!directory.exists()) {
    // return classes;
    // }
    // File[] files = directory.listFiles();
    // for (File file : files) {
    // if (file.isDirectory()) {
    // assert !file.getName().contains(".");
    // classes.addAll(findClasses(file, packageName + "." + file.getName()));
    // } else if (file.getName().endsWith(".class")) {
    // classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
    // }
    // }
    // return classes;
    // }
}
