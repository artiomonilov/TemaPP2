package org.example;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import java.util.Set;
import org.graalvm.polyglot.Source;
import java.nio.file.Path;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws Exception {
//        Path jsPath = Path.of("src/main/java/org/example/primes.js");
//        String jsCode = Files.readString(jsPath);

//        Path pyPath = Path.of("src/main/java/org/example/primes.py");
//        String pyCode = Files.readString(pyPath);

        Path pyPath = Path.of("src/main/java/org/example/random.py");
        String pyCode = Files.readString(pyPath);


        Path jsPath = Path.of("src/main/java/org/example/showList.js");
        String jsCode = Files.readString(jsPath);

//        System.out.println("Asteptam executarea codului JavaScript");
//        try (Context context = Context.newBuilder("js").allowAllAccess(true).build()) {
//            context.eval(Source.newBuilder("js", jsCode, "primes.js").build());
//        }

//        System.out.println("Asteptam executarea codului Python... ");
//        try(Context context = Context.newBuilder("python").allowAllAccess(true).build() ) {
//            context.eval(Source.newBuilder("python", pyCode, "primes.py").build());
//        }

//        System.out.println("Asteptam executarea codului Python... ");
//        try(Context context = Context.newBuilder("python").allowAllAccess(true).build() ) {
//            context.eval(Source.newBuilder("python", pyCode, "random.py").build());
//        }



        try(Context context = Context.newBuilder().allowAllAccess(true).build())
        {
            Value pyBindings = context.getBindings("python");
            context.eval(Source.newBuilder("python", pyCode, "random.py").build());
            Value lista = pyBindings.getMember("lista");

            context.getBindings("js").putMember("lista", lista);
            context.eval("js", jsCode);


            context.getBindings("python").putMember("lista", lista);
            String sortPy = Files.readString(Path.of("src/main/java/org/example/sortList.py"));
            context.eval("python", sortPy);

        }









//        try (Context context = Context.newBuilder().allowAllAccess(true).build()) {
//
//
//            Set<String> languages = context.getEngine().getLanguages().keySet();
//
//            for (String id : languages) {
//                System.out.println("Initializing language " + id);
//                context.initialize(id);
//                switch (id) {
//                    case "python":
//                        context.eval("python", "print('Hello Python!')");
//                        break;
//                    case "js":
//                        context.eval("js", "print('Hello JavaScript!');");
//                        break;
//                    case "ruby":
//                        context.eval("ruby", "puts 'Hello Ruby!'");
//                        break;
//                    case "java":
//                        Value out = context.getBindings("java").getMember("java.lang.System").getMember("out");
//                        out.invokeMember("println", "Hello Espresso Java!");
//                        break;
//                }
//            }
//        }
    }
}