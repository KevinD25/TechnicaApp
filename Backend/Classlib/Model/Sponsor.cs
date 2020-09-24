using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Text;

namespace Classlib
{
    public class Sponsor
    {
        public int id { get; set; }
        public string name { get; set; }
        public string about { get; set; }
        public string website { get; set; }
        public string imageLink { get; set; }
        public IList<Vacature> vacatures { get; set; }
    }
}
