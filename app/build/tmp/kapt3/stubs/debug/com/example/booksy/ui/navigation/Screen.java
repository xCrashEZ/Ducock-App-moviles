package com.example.booksy.ui.navigation;

/**
 * Rutas de navegación de la aplicación
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \b2\u00020\u0001:\b\u0007\b\t\n\u000b\f\r\u000eB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0007\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u00a8\u0006\u0016"}, d2 = {"Lcom/example/booksy/ui/navigation/Screen;", "", "route", "", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "BookDetail", "Companion", "Downloads", "Home", "Login", "Profile", "Register", "Search", "Lcom/example/booksy/ui/navigation/Screen$BookDetail;", "Lcom/example/booksy/ui/navigation/Screen$Downloads;", "Lcom/example/booksy/ui/navigation/Screen$Home;", "Lcom/example/booksy/ui/navigation/Screen$Login;", "Lcom/example/booksy/ui/navigation/Screen$Profile;", "Lcom/example/booksy/ui/navigation/Screen$Register;", "Lcom/example/booksy/ui/navigation/Screen$Search;", "app_debug"})
public abstract class Screen {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ROOT_GRAPH_ROUTE = "root";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String AUTH_GRAPH_ROUTE = "auth";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MAIN_GRAPH_ROUTE = "main";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.booksy.ui.navigation.Screen.Companion Companion = null;
    
    private Screen(java.lang.String route) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u00a8\u0006\u0006"}, d2 = {"Lcom/example/booksy/ui/navigation/Screen$BookDetail;", "Lcom/example/booksy/ui/navigation/Screen;", "()V", "createRoute", "", "bookId", "app_debug"})
    public static final class BookDetail extends com.example.booksy.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.booksy.ui.navigation.Screen.BookDetail INSTANCE = null;
        
        private BookDetail() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String createRoute(@org.jetbrains.annotations.NotNull()
        java.lang.String bookId) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/example/booksy/ui/navigation/Screen$Companion;", "", "()V", "AUTH_GRAPH_ROUTE", "", "MAIN_GRAPH_ROUTE", "ROOT_GRAPH_ROUTE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/booksy/ui/navigation/Screen$Downloads;", "Lcom/example/booksy/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Downloads extends com.example.booksy.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.booksy.ui.navigation.Screen.Downloads INSTANCE = null;
        
        private Downloads() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/booksy/ui/navigation/Screen$Home;", "Lcom/example/booksy/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Home extends com.example.booksy.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.booksy.ui.navigation.Screen.Home INSTANCE = null;
        
        private Home() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/booksy/ui/navigation/Screen$Login;", "Lcom/example/booksy/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Login extends com.example.booksy.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.booksy.ui.navigation.Screen.Login INSTANCE = null;
        
        private Login() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/booksy/ui/navigation/Screen$Profile;", "Lcom/example/booksy/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Profile extends com.example.booksy.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.booksy.ui.navigation.Screen.Profile INSTANCE = null;
        
        private Profile() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/booksy/ui/navigation/Screen$Register;", "Lcom/example/booksy/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Register extends com.example.booksy.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.booksy.ui.navigation.Screen.Register INSTANCE = null;
        
        private Register() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/booksy/ui/navigation/Screen$Search;", "Lcom/example/booksy/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Search extends com.example.booksy.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.example.booksy.ui.navigation.Screen.Search INSTANCE = null;
        
        private Search() {
        }
    }
}