using System;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Classlib;
using Classlib.Services;
using Microsoft.AspNetCore.Http;



namespace TECHNICATEST.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ClubTextController : ControllerBase
    {
        public TAContext _ctx { get; set; }

        public ClubTextController(TAContext ctx)
        {
            _ctx = ctx;
        }

        [HttpGet]
        public ActionResult<ClubText> GetClubText()
        {
            var text = _ctx.ClubTexts.First();
            if (text == null)
                return NotFound();
      
            return text;
        }
        [HttpPut]
        public ActionResult<ClubText> EditClubText([FromBody] ClubText TXT)
        {
            _ctx.ClubTexts.Update(TXT);
            _ctx.SaveChanges();
            return Created("", TXT);
        }
        [HttpPost]
        public ActionResult<ClubText> AddClubText([FromBody] ClubText TXT)
        {
            var T = _ctx.ClubTexts;
            if(T != null)
            {
                foreach (var Txt in T)
                {

                    _ctx.ClubTexts.Remove(Txt);

                }
            }

            _ctx.ClubTexts.Add(TXT);
            _ctx.SaveChanges();
            return Created("", TXT);
        }


    }
}
