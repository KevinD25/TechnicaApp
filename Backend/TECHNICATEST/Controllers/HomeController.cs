using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Classlib;
using Microsoft.AspNetCore.Mvc;


namespace TECHNICATEST.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class HomeController : Controller
    {
        public TAContext _ctx { get; set; }

        public HomeController(TAContext ctx)
        {
            _ctx = ctx;
        }

        [HttpGet]
        public ActionResult<Home> GetHomeImg()
        {

            var I = _ctx.Home.Where(A => DateTime.Compare(A.date,DateTime.Now) > 0).OrderBy(A => A.date).FirstOrDefault();
   
            if (I == null)
                return NotFound();

            return I;
        }


      
        [HttpPut]
        public ActionResult<Home> EditHome([FromBody] Home h)
        {
            _ctx.Home.Update(h);
            _ctx.SaveChanges();
            return Created("", h);
        }



        [HttpPost]
        public ActionResult<Home> AddHome([FromBody] Home h)
        {
            _ctx.Home.Add(h);
            _ctx.SaveChanges();
            return Created("", h);
        }


        [Route("{id}")]
        [HttpDelete]
        public ActionResult<Home> DeleteHome(int ID)
        {
            var Home = _ctx.Home.Find(ID);
            if (Home == null)
                return NotFound();

            _ctx.Home.Remove(Home);
            _ctx.SaveChanges();
            return NoContent();
        }
    }
}
