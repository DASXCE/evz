package fit.piris.evz.services;

import org.apache.tapestry5.*;
import org.apache.tapestry5.hibernate.HibernateTransactionAdvisor;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.services.ComponentRequestFilter;
import org.apache.tapestry5.services.ComponentRequestHandler;
import org.apache.tapestry5.validator.ValidatorMacro;
import org.got5.tapestry5.jquery.JQuerySymbolConstants;

import fit.piris.evz.services.dao.gazdinstvo.GazdinstvoDAO;
import fit.piris.evz.services.dao.user.UserDAO;
import fit.piris.evz.services.dao.user.UserDAOImpl;
import fit.piris.evz.services.dao.zivotinja.ZivotinjaDAO;
import fit.piris.evz.services.security.AuthenticationFilter;
import fit.piris.evz.services.security.Authenticator;

/**
 * This module is automatically included as part of the Tapestry IoC Registry,
 * it's a good place to configure and extend Tapestry, or to place your own
 * service definitions.
 */
public class AppModule {
	public static void bind(ServiceBinder binder) {
		// binder.bind(MyServiceInterface.class, MyServiceImpl.class);

		// Make bind() calls on the binder object to define most IoC services.
		// Use service builder methods (example below) when the implementation
		// is provided inline, or requires more initialization than simply
		// invoking the constructor.
		binder.bind(Authenticator.class);// moze i ovako, tapestry trazi u istom
											// paketu sa istim imenom i sufiksom
											// Impl
		binder.bind(UserDAO.class, UserDAOImpl.class);
		binder.bind(GazdinstvoDAO.class);
		binder.bind(ZivotinjaDAO.class);
	}

	public static void contributeFactoryDefaults(
			MappedConfiguration<String, Object> configuration) {
		// The application version number is incorprated into URLs for some
		// assets. Web browsers will cache assets because of the far future
		// expires
		// header. If existing assets are changed, the version number should
		// also
		// change, to force the browser to download new versions. This overrides
		// Tapesty's default
		// (a random hexadecimal number), but may be further overriden by
		// DevelopmentModule or
		// QaModule.
		configuration.override(SymbolConstants.APPLICATION_VERSION,
				"1.0-SNAPSHOT");
	}

	public static void contributeApplicationDefaults(
			MappedConfiguration<String, Object> configuration) {
		// Contributions to ApplicationDefaults will override any contributions
		// to
		// FactoryDefaults (with the same key). Here we're restricting the
		// supported
		// locales to just "en" (English). As you add localised message catalogs
		// and other assets,
		// you can extend this list of locales (it's a comma separated series of
		// locale names;
		// the first locale name is the default when there's no reasonable
		// match).
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
		configuration.add(JQuerySymbolConstants.SUPPRESS_PROTOTYPE, true);

		// Tynamo's tapestry-security module configuration
		// configuration.add(SecuritySymbols.LOGIN_URL, "/signin");
		// configuration.add(SecuritySymbols.UNAUTHORIZED_URL, "/blocked");
		// configuration.add(SecuritySymbols.SUCCESS_URL, "/logged");
	}

	/**
	 * This is a service definition, the service will be named "TimingFilter".
	 * The interface, RequestFilter, is used within the RequestHandler service
	 * pipeline, which is built from the RequestHandler service configuration.
	 * Tapestry IoC is responsible for passing in an appropriate Logger
	 * instance. Requests for static resources are handled at a higher level, so
	 * this filter will only be invoked for Tapestry related requests.
	 * <p/>
	 * <p/>
	 * Service builder methods are useful when the implementation is inline as
	 * an inner class (as here) or require some other kind of special
	 * initialization. In most cases, use the static bind() method instead.
	 * <p/>
	 * <p/>
	 * If this method was named "build", then the service id would be taken from
	 * the service interface and would be "RequestFilter". Since Tapestry
	 * already defines a service named "RequestFilter" we use an explicit
	 * service id that we can reference inside the contribution method.
	 */
	// public RequestFilter buildTimingFilter(final Logger log) {
	// return new RequestFilter() {
	// public boolean service(Request request, Response response,
	// RequestHandler handler) throws IOException {
	// long startTime = System.currentTimeMillis();
	//
	// try {
	// // The responsibility of a filter is to invoke the
	// // corresponding method
	// // in the handler. When you chain multiple filters together,
	// // each filter
	// // received a handler that is a bridge to the next filter.
	//
	// return handler.service(request, response);
	// } finally {
	// long elapsed = System.currentTimeMillis() - startTime;
	//
	// log.info(String.format("Request time: %d ms", elapsed));
	// }
	// }
	// };
	// }

	/**
	 * This is a contribution to the RequestHandler service configuration. This
	 * is how we extend Tapestry using the timing filter. A common use for this
	 * kind of filter is transaction management or security. The @Local
	 * annotation selects the desired service by type, but only from the same
	 * module. Without @Local, there would be an error due to the other
	 * service(s) that implement RequestFilter (defined in other modules).
	 */
	// public void contributeRequestHandler(
	// OrderedConfiguration<RequestFilter> configuration,
	// @Local RequestFilter filter) {
	// // Each contribution to an ordered configuration has a name, When
	// // necessary, you may
	// // set constraints to precisely control the invocation order of the
	// // contributed filter
	// // within the pipeline.
	//
	// configuration.add("Timing", filter);
	// }

	@Match("*DAO")
	/*
	 * za commit after, samo za DAO klase radi sigurnosti da ne moze niko drugi
	 * osim DAO da vrsi promjene nad bazom
	 */
	public static void adviseTransactions(HibernateTransactionAdvisor advisor,
			MethodAdviceReceiver receiver) {
		advisor.addTransactionCommitAdvice(receiver);
	}

	/*
	 * serverska validacija
	 */
	@Contribute(ValidatorMacro.class)
	public static void combineValidators(
			MappedConfiguration<String, String> configuration) {
		configuration.add("email", "required, minlength=3, maxlength=20");
		configuration.add("password", "required, minlength=4, maxlength=20");
	}

	@Contribute(ComponentRequestHandler.class)
	public static void contributeComponentRequestHandler(
			OrderedConfiguration<ComponentRequestFilter> configuration) {
		configuration.addInstance("RequiresLogin", AuthenticationFilter.class);
	}

	// /*
	// * Tynamo
	// */
	//
	// @Contribute(WebSecurityManager.class)
	// public static void addRealms(Configuration<Realm> configuration) {
	// ExtendedPropertiesRealm realm = new
	// ExtendedPropertiesRealm("classpath:shiro-users.properties");
	// configuration.add(realm);
	// }
	//
	// // Starting from 0.4.6, you can also use a marker annotation:
	// // @Contribute(HttpServletRequestFilter.class) @Security public static
	// void securePaths(...)
	// public static void
	// contributeSecurityConfiguration(Configuration<SecurityFilterChain>
	// configuration,
	// SecurityFilterChainFactory factory) {
	// // /authc/** rule covers /authc , /authc?q=name /authc#anchor urls as
	// well
	// configuration.add(factory.createChain("/authc/signup").add(factory.anon()).build());
	// configuration.add(factory.createChain("/authc/**").add(factory.authc()).build());
	// configuration.add(factory.createChain("/contributed/**").add(factory.authc()).build());
	// configuration.add(factory.createChain("/user/signup").add(factory.anon()).build());
	// configuration.add(factory.createChain("/user/**").add(factory.user()).build());
	// configuration.add(factory.createChain("/roles/user/**").add(factory.roles(),
	// "user").build());
	// configuration.add(factory.createChain("/roles/manager/**").add(factory.roles(),
	// "manager").build());
	// configuration.add(factory.createChain("/perms/view/**").add(factory.perms(),
	// "news:view").build());
	// configuration.add(factory.createChain("/perms/edit/**").add(factory.perms(),
	// "news:edit").build());
	// }
}
