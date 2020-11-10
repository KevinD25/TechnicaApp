using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Classlib.Repos;
using Classlib.Services;
using Newtonsoft.Json;
using Microsoft.AspNetCore.Authentication.JwtBearer;

namespace TECHNICATEST
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddCors(options =>
            {
                options.AddPolicy("CorsPolicy",
                    builder => builder.AllowAnyOrigin()
                    .AllowAnyMethod()
                    .AllowAnyHeader()
                    );

            });
            services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme).AddJwtBearer(options =>
            {
                options.Authority = "https://dev-skr3fnrj.eu.auth0.com";
                options.Audience = "3V3VJhjWHGmjLaXt0EuxPMVlUCuM2xJE";
            });
            services.AddMvc(option => option.EnableEndpointRouting = false)
                .SetCompatibilityVersion(CompatibilityVersion.Version_3_0)
                .AddNewtonsoftJson(opt => opt.SerializerSettings.ReferenceLoopHandling = ReferenceLoopHandling.Ignore); ;
            services.AddDbContext<TAContext>(
                options => options.UseSqlServer(
                    Configuration.GetConnectionString("DefaultConnection")));
           /* services.AddScoped<ClubTextRepo, ClubTextRepo>();
            services.AddScoped<HomeRepo, HomeRepo>();
            services.AddScoped<PraesidiumRepo, PraesidiumRepo>();
            services.AddScoped<SponsorRepo, SponsorRepo>();



            services.AddScoped<ClubTextService, ClubTextService>();
           services.AddScoped<HomeService, HomeService>();
            services.AddScoped<PraesidiumService, PraesidiumService>();
            services.AddScoped<SponsorService, SponsorService>();
            */
          
           
            services.AddControllers();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, Microsoft.AspNetCore.Hosting.IHostingEnvironment env, TAContext context)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

          



            app.UseAuthorization();
            app.UseCors("CorsPolicy");
            app.UseAuthentication();
            app.UseMvc();


            DBInit.Initialize(context);

        }
    }
}
