package sample.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
// application.properties 를 사용할 수 있도록 설정 파일의 위치를 지정
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {
	
	@Autowired
	private ApplicationContext context;
	
	@Bean
	// application.properties에 설정했던 디비 관련 정보를 사용하도록 지정.
	// @ConfigurationProperties의 prefix를 이용해서 히카리 커넥션 풀의 설정 파일을 만든다.
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	
	@Bean
	public DataSource dataSource()throws Exception{
		// 위에서 만든 히카리 커넥션 풀의 설정 파일을 이용해서 디비와 연결하는 datasource를 생성한다.
		DataSource dataSource = new HikariDataSource(hikariConfig());
		if(dataSource.toString()!=null) {
			System.out.println("히카리 데이터소스 정상적으로 생성");
		}
		System.out.println("히카리 데이터소스 ::"+dataSource.toString());
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource ds)throws Exception{
		// 스프링에서 마이바티스를 사용할 때 SqlSessionFactory를 사용한다.
		// 팩토리 빈을 생성하기 위해 SqlSessionFactory을 사용한다.
		System.out.println("sqlSession factory 생성");
		System.out.println("sqlSessionFactory에 가져온 데이터소스 ::"+ds);
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();  
		bean.setDataSource(ds);
		// 매퍼 파일의 위치를 설정한다.
		// 많은 수의 매퍼파일을 등록할 때에는 일일이 등록하기 어려우므로 패턴을 기반으로 등록한다.
		bean.setMapperLocations(context.getResources("classpath:/mapper/**/sql-*.xml"));
		bean.setConfiguration(mybatisConfig());
		return bean.getObject();
	}
	
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory) {
		System.out.println("sqlSessionTemplate 에 가져온 factory::"+factory);
		return new SqlSessionTemplate(factory);
	}
	
	//application.properties 에서 설정해 준 map-underscore-to -camelcase 를 적용시켜준다.
	@Bean
	@ConfigurationProperties(prefix="mybatis.configuration") // 프로퍼티스 파일에 있는 설정 중 마이바티스 설정을 가져온다.
	public org.apache.ibatis.session.Configuration mybatisConfig(){
		System.out.println("mybatisConfig() 호출");
		return new org.apache.ibatis.session.Configuration(); //가져온 마이바티스 설정을 자바 클래스로 만들어서 반환한다.
	}
	
	
	
	
	
	
	
	
	
	
	

}
